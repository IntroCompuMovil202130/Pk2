package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Descripcion_habitacion extends AppCompatActivity {

    TextView nombre, descripcion, precio, temp;
    ImageView img1, img2, img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_habitacion);
        //inflate
        nombre = findViewById(R.id.nombreHabitacion);
        descripcion = findViewById(R.id.descripcionHabitacion);
        temp = findViewById(R.id.temperaturaHa);
        precio = findViewById(R.id.precioHabitacion);
        img1 = findViewById(R.id.imagen1Habitacion);
        img2 = findViewById(R.id.imagen2Habitacion);
        img3 = findViewById(R.id.imagen3Habitacion);

        nombre.setText(getIntent().getStringExtra("nombre"));
        descripcion.setText(getIntent().getStringExtra("des"));
        temp.setText("Temperatura: "+ getIntent().getStringExtra("temp") + "°C");
        precio.setText("Precio: $"+ getIntent().getStringExtra("precio")+ " / " + getIntent().getStringExtra("hora"));
        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra("img1"))
                .into(img1);
        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra("img2"))
                .into(img2);
        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra("img3"))
                .into(img3);
    }
}