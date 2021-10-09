package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pk2.model.Motel;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class crear_motel extends AppCompatActivity {

    TextView nombre;
    TextView direccion;
    //Base de datos
    FirebaseDatabase database;
    DatabaseReference myRef;
    static final String PATH_MOTEL = "motel/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_motel);
        nombre = findViewById(R.id.inputMotelNom);
        direccion = findViewById(R.id.inputMotelAdd);
        database = FirebaseDatabase.getInstance();
    }
    public void regist(View v)
    {
        String nom = nombre.getText().toString();
        String add = direccion.getText().toString();
        String id = getIntent().getStringExtra("cedula");
        if(!nom.isEmpty() && !add.isEmpty()) {
            Motel motel = new Motel();
            motel.setNombre(nom);
            motel.setDireccion(add);
            motel.setId(id);
            myRef = database.getReference(PATH_MOTEL);
            //asignacion de cc como key
            myRef = database.getReference(PATH_MOTEL + id);
            //escritura
            myRef.setValue(motel);
            Intent intent = new Intent(crear_motel.this,LogIn.class);
            startActivity(intent);
        }

    }

}