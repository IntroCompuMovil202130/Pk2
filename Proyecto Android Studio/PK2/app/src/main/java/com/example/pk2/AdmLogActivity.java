package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdmLogActivity extends AppCompatActivity {
    Button btnAgHab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_log);
        btnAgHab = findViewById(R.id.btnAgHab);
        btnAgHab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intCrearHab = new Intent(v.getContext(),crear_habitacon.class);
                startActivity(intCrearHab);
            }
        });
    }
}
