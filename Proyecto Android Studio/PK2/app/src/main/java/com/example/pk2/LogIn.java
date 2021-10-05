package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LogIn extends AppCompatActivity {

    Button bLogin;
    Button bReg;
    Button bReg2;
    TextInputEditText usuario, contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.moraitoMelo));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //inflate
        bLogin = findViewById(R.id.botonLogin);
        bReg = findViewById(R.id.botonRegister);
        bReg2 = findViewById(R.id.botonRegister2);
        usuario = findViewById(R.id.textoUsuario);
        contraseña = findViewById(R.id.textoContasenna);
        //llamado pantalla register

        bReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Registro.class);
                startActivity(intent);
            }
        });

        //llamado a el registro del duenio
        bReg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Registro_duenio.class);
                startActivity(intent);
            }
        });
        //llamado pantalla login

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usuario.getText().toString().equals("adm"))
                {
                    Intent intent = new Intent(v.getContext(), AdmLogActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(v.getContext(), ListaMoteles.class);
                    startActivity(intent);
                }
            }
        });
    }
}