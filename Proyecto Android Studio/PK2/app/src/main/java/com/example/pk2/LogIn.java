package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class LogIn extends AppCompatActivity {

    Button bLogin;
    Button bRegUsr;
    Button bRegDue;
    TextInputEditText usuario, contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.moraitoMelo));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //inflate
        bRegUsr = findViewById(R.id.usarioRegist);
        bRegDue = findViewById(R.id.dueñoRegist);
        usuario = findViewById(R.id.correoLogin);
        contraseña = findViewById(R.id.passwLogin);
        //llamado pantalla register

        bRegUsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Registro.class);
                startActivity(intent);
            }
        });

        //llamado a el registro del duenio
        bRegDue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Registro_duenio.class);
                startActivity(intent);
            }
        });
    }
}