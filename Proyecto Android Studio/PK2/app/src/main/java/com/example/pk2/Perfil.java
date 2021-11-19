package com.example.pk2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pk2.model.Motel;
import com.example.pk2.model.MotelElementoList;
import com.example.pk2.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Perfil extends AppCompatActivity {

    //variables
    Button salir, temperatura;
    TextView nombre, cedula, correo;
    //autenticacion firebase
    FirebaseAuth mAuth;
    static final String PATH_USERS = "users/";
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getWindow().setStatusBarColor(getResources().getColor(R.color.moraitoMelo));
        //inflates
        salir = findViewById(R.id.botonLogOut);
        temperatura = findViewById(R.id.botonTemperatura);
        nombre = findViewById(R.id.perfilNombre);
        cedula = findViewById(R.id.perfilCedula);
        correo = findViewById(R.id.perfilCorreo);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(Perfil.this,LogIn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Perfil.this.finish();
            }
        });
        temperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this,Temperatura.class);
                startActivity(intent);
            }
        });
        cargar(mAuth.getCurrentUser());
    }
    private void cargar(FirebaseUser usuario2)
    {
        myRef = database.getReference(PATH_USERS);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    Usuario usuario = child.getValue(Usuario.class);
                    if (usuario.getId().equals(usuario2.getUid())){
                        nombre.setText(usuario.getNombre());
                        cedula.setText(usuario.getCedula());
                        correo.setText(usuario.getCorreo());
                    }
                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            };

        });
    }
}