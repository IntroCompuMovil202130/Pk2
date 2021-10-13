package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lista_habitaciones extends AppCompatActivity {

    CardView selecHabitacion;
    Button Bvamos;
    TextView txDireccion, txNombreMotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_habitaciones);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //inflate
        selecHabitacion = findViewById(R.id.habSencilla);
        Bvamos = findViewById(R.id.btnEditInf);
        txDireccion = findViewById(R.id.textoDireccion);
        txNombreMotel = findViewById(R.id.textoHabitacionesNombreMotel);

        // asingancion de valores
        txDireccion.setText("Cl. 62 #14 - 31");
        txNombreMotel.setText("Motel Las Palmas");

        Bvamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intVAmos = new Intent(v.getContext(),Mapa.class);
                intVAmos.putExtra("Direccion", txDireccion.getText());
                intVAmos.putExtra("NombreMotel", txNombreMotel.getText());
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