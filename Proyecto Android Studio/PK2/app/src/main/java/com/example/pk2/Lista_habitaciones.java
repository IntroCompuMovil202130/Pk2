package com.example.pk2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pk2.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Lista_habitaciones extends AppCompatActivity {

    CardView selecHabitacion;
    Button Bvamos;
    TextView txDireccion, txNombreMotel;
    static final String PATH_USER = "users/";
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
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
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Log.e("pene", "penesito");
        Toast.makeText(this, "pene", Toast.LENGTH_SHORT).show();
        System.out.println("pene");
        cargar(mAuth.getCurrentUser());

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
    private void cargar(FirebaseUser usuario)
    {
        myRef = database.getReference(PATH_USER);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()) {
                    Usuario user = child.getValue(Usuario.class);
                    Log.e("pene",usuario.toString() );
                    System.out.println(usuario.toString()+ "pene");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}