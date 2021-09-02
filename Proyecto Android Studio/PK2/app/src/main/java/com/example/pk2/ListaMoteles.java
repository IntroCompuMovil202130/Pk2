package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ListaMoteles extends AppCompatActivity {
    ImageButton bPerfil;
    CardView selecMotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_moteles);

        //inflate
        bPerfil = findViewById(R.id.botonPerifl);
        selecMotel = findViewById(R.id.cardView3);

        bPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Perfil.class);
                startActivity(intent);
            }
        });

        selecMotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Lista_habitaciones.class);
                startActivity(intent);
            }
        });

    }
}