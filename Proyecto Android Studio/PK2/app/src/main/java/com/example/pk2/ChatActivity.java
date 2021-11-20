package com.example.pk2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pk2.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatActivity extends AppCompatActivity {

    //autenticacion
    FirebaseAuth mAuth;
    //Base de datos
    FirebaseDatabase database;
    DatabaseReference myRefU,myRefD;
    //Ruta en la que estan los usuarios y dueños
    static final String PATH_USERS = "users/";
    static final String PATH_DUENO = "dueno/";


    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().getWindowInsetsController().setSystemBarsAppearance(0x00000008, 0x00000008);
        //inflate base de datos y autenticacion
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
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
        myRefU = database.getReference(PATH_DUENO);
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