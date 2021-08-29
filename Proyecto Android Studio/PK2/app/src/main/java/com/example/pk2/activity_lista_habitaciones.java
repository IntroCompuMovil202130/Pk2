package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_lista_habitaciones extends AppCompatActivity {

    CardView selecHabitacion;
    Button Bvamos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_habitaciones);

        //inflate
        selecHabitacion = findViewById(R.id.habSencilla);
        Bvamos = findViewById(R.id.botonVamos);
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
                Intent intent = new Intent(v.getContext(), activity_descripcion_habitacion.class);
                startActivity(intent);
            }
        });

    }
}