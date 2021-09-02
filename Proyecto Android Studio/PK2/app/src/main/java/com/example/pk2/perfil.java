package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getWindow().setStatusBarColor(getResources().getColor(R.color.moraitoMelo));
    }
}