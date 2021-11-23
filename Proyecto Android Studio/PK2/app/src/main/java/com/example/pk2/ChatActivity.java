package com.example.pk2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.pk2.model.Chat;
import com.example.pk2.model.Mensaje_Texto;
import com.example.pk2.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    //autenticacion
    FirebaseAuth mAuth;
    //Base de datos
    FirebaseDatabase database;
    DatabaseReference myRefU,myRefD;
    //Ruta en la que estan los usuarios y dueños
    static final String PATH_USERS = "users/";
    static final String PATH_DUENO = "dueno/";

    private AdaptadorChat a_Chat;

    private RecyclerView r_ListMensajes;

    private List<Mensaje_Texto> testList;

    Usuario mySender, myReciever;

    Intent currIntent;

    boolean esDueno;

    TextInputEditText e_Text;

    ImageButton b_Send;


    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //inflate base de datos y autenticacion
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        currIntent = getIntent();
        esDueno = currIntent.getBooleanExtra("esDueno", false);
        getUsers();
        b_Send = findViewById(R.id.botonEnviarChat);
        e_Text = findViewById(R.id.messageTextChat);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().getWindowInsetsController().setSystemBarsAppearance(0x00000008, 0x00000008);

        b_Send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(e_Text.getText().toString().trim()))
                {
                    Date date = new Date();
                    Mensaje_Texto nuevoMensaje = new Mensaje_Texto(e_Text.getText().toString(), mySender, date.getTime());
                    sendMessage(nuevoMensaje);
                    e_Text.setText("");
                }
            }
        });

    }
    /*inflate del menu y manejo de las opciones de este*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        myRefD = database.getReference(PATH_DUENO);
        comprobarU(menu);
        comprobarD(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemClicked = item.getItemId();
        if(itemClicked == R.id.ubicacionOn)
        {
            ubicacionOn();
        }
        if(itemClicked == R.id.ubicacionOff)
        {
            ubicacionOff();
        }
        if(itemClicked == R.id.ubiUser)
        {
            Intent intent = new Intent(ChatActivity.this,VerUbicacionClienteActivity.class);
            intent.putExtra("nombreMot",getIntent().getStringExtra("nombrMot"));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }

    private void comprobarD(Menu menu){
        myRefD = database.getReference(PATH_DUENO);
        myRefD.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren())
                {
                    Usuario actual = child.getValue(Usuario.class);
                    if (actual.getId().equals(mAuth.getUid()))
                    {
                        getMenuInflater().inflate(R.menu.duenomenu, menu);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void comprobarU(Menu menu)
    {
        myRefU = database.getReference(PATH_USERS);
        myRefU.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren())
                {
                    Usuario actual = child.getValue(Usuario.class);
                    if (actual.getId().equals(mAuth.getUid()))
                    {
                        getMenuInflater().inflate(R.menu.usermenu, menu);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ubicacionOn()
    {
        myRefU = database.getReference(PATH_USERS);
        myRefU.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren())
                {
                    Usuario actual = child.getValue(Usuario.class);
                    if(actual.getId().equals(mAuth.getUid()))
                    {
                        actual.setUbi(true);
                        myRefU.child(actual.getId()).setValue(actual);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void sendMessage(Mensaje_Texto nuevoMensaje)
    {
        myRefU = database.getReference(PATH_USERS);
        a_Chat.addMessage(nuevoMensaje);
        if (esDueno)
        {

            myRefU.orderByChild("id").equalTo(myReciever.getId()).addListenerForSingleValueEvent(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    for (DataSnapshot child : snapshot.getChildren()){
                        Usuario currUser = child.getValue(Usuario.class);
                        if(!currUser.doesChatExists(mySender.getId()))
                        {
                            Chat myNewChat = new Chat(mySender.getId());
                            myReciever.agregarChat(myNewChat);
                        }
                        myReciever.agregarMensaje(nuevoMensaje, mySender.getId());
                        myRefU.child(myReciever.getId()).setValue(myReciever);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        else
        {

            myRefU.orderByChild("id").equalTo(mySender.getId()).addListenerForSingleValueEvent(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    for (DataSnapshot child : snapshot.getChildren())
                    {
                        Usuario currUser = child.getValue(Usuario.class);
                        if(!currUser.doesChatExists(myReciever.getId()))
                        {
                            Chat myNewChat = new Chat(myReciever.getId());
                            mySender.agregarChat(myNewChat);
                            myRefU.child(mySender.getId()).child("/listChats/").child("0/").setValue(myNewChat);

                        }
                        mySender.agregarMensaje(nuevoMensaje, myReciever.getId());
                        
                        myRefU.child(mySender.getId()).child("/listChats/").child("noMensajes/").setValue(nuevoMensaje);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
    private void displayList()
    {

        r_ListMensajes = findViewById(R.id.chat_recycler_view);
        if (esDueno)
        {
            //Inflar reciclador y crear adaptador.
            a_Chat = new AdaptadorChat(this, myReciever.getMessageList(mySender.getId()));
            r_ListMensajes.setLayoutManager(new LinearLayoutManager(this));
            r_ListMensajes.setAdapter(a_Chat);

        }
        else
        {
            //Inflar reciclador y crear adaptador.
            a_Chat = new AdaptadorChat(this, mySender.getMessageList(myReciever.getId()));
            r_ListMensajes.setLayoutManager(new LinearLayoutManager(this));
            r_ListMensajes.setAdapter(a_Chat);
        }
    }

    private void getUsers()
    {
        myRefU = database.getReference(PATH_USERS);
        myRefD = database.getReference(PATH_DUENO);
        Log.d("TEST_A",currIntent.getStringExtra("idReciever"));
        if (esDueno)
        {
            myRefD.addListenerForSingleValueEvent(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot child : snapshot.getChildren())
                    {
                        Usuario buscado = child.getValue(Usuario.class);
                        FirebaseUser logeado = mAuth.getCurrentUser();
                        if(buscado.getId().equals(logeado.getUid()))
                        {
                            mySender = buscado;
                        }
                    }
                    myRefU.addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot child : snapshot.getChildren())
                            {
                                Usuario buscado = child.getValue(Usuario.class);
                                if(buscado.getId().equals(currIntent.getStringExtra("idReciever")))
                                {
                                    myReciever = buscado;
                                }
                            }
                            displayList();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        else
        {
            myRefU.addListenerForSingleValueEvent(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot child : snapshot.getChildren())
                    {
                        Usuario buscado = child.getValue(Usuario.class);
                        FirebaseUser logeado = mAuth.getCurrentUser();
                        if(buscado.getId().equals(logeado.getUid()))
                        {
                            mySender = buscado;
                        }
                    }
                    myRefD.addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot child : snapshot.getChildren())
                            {
                                Usuario buscado = child.getValue(Usuario.class);

                                Log.d("TEST_A", buscado.getId());
                                if(buscado.getId().equals(currIntent.getStringExtra("idReciever")))
                                {
                                    myReciever = buscado;
                                }
                            }
                            displayList();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }


    }
    private void ubicacionOff()
    {
        myRefU = database.getReference(PATH_USERS);
        myRefU.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren())
                {
                    Usuario actual = child.getValue(Usuario.class);
                    if(actual.getId().equals(mAuth.getUid()))
                    {
                        actual.setUbi(false);
                        myRefU.child(actual.getId()).setValue(actual);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
