package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Registro_duenio extends AppCompatActivity {
    
    Button registro;
    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_duenio);
        getWindow().setStatusBarColor(getResources().getColor(R.color.moraitoMelo));
        registro = findViewById(R.id.botonRegistro);
        nombre = findViewById(R.id.nombreInputRegi);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(nombre.getText().toString());
            }
        });
    }
}