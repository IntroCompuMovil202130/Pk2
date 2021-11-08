package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AdmLogActivity extends AppCompatActivity {
    Button btnAgHab, botonCerrarSesion;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_log);
        btnAgHab = findViewById(R.id.btnAgHab);
        botonCerrarSesion = findViewById(R.id.logOutDuenio);
        mAuth = FirebaseAuth.getInstance();
        btnAgHab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intCrearHab = new Intent(v.getContext(),crear_habitacon.class);
                startActivity(intCrearHab);
            }
        });
        botonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(AdmLogActivity.this,LogIn.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                AdmLogActivity.this.finish();
            }
        });
    }
}
