package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Lista_habitaciones extends AppCompatActivity {

    CardView selecHabitacion;
    Button Bvamos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_habitaciones);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //inflate
        selecHabitacion = findViewById(R.id.habSencilla);
        Bvamos = findViewById(R.id.btnEditInf);
        Bvamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intVAmos = new Intent(v.getContext(),MapsActivity.class);
                startActivity(intVAmos);
            }
        });
        selecHabitacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Descripcion_habitacion.class);
                startActivity(intent);
            }
        });

    }
}