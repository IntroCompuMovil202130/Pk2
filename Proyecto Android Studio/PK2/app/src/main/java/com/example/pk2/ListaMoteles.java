package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ListaMoteles extends AppCompatActivity {
    ImageButton bPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_moteles);
        getWindow().setStatusBarColor(getResources().getColor(R.color.moraitoMelo));

        //inflate
        bPerfil = findViewById(R.id.botonPerifl);

        bPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), perfil.class);
                startActivity(intent);
            }
        });
    }
}