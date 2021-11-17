package com.example.pk2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pk2.model.Dueno;
import com.example.pk2.model.Motel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdmLogActivity extends AppCompatActivity {
    Button btnAgHab, botonCerrarSesion;
    TextView nombreMotel, direccionMotel;
    ImageView portada;
    static final String PATH_USER = "dueno/";
    static final String PATH_MOTEL = "motel/";
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_log);
        //inflate
        btnAgHab = findViewById(R.id.btnAgHab);
        nombreMotel = findViewById(R.id.textoHabitacionesNombreMotel);
        portada = findViewById(R.id.portada);
        direccionMotel = findViewById(R.id.textoDireccion);
        botonCerrarSesion = findViewById(R.id.logOutDuenio);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Log.e("pene", "penesito");
        Toast.makeText(this, "pene", Toast.LENGTH_SHORT).show();
        System.out.println("pene");
        cargar(mAuth.getCurrentUser());
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
    private void cargar(FirebaseUser usuario)
    {
        myRef = database.getReference(PATH_MOTEL);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()) {
                    Motel motel = child.getValue(Motel.class);
                    Log.e("pene", usuario.getUid());
                    if(usuario.getUid().equals(motel.getId())){
                        String urlImage = motel.getDirImagen();
                        Glide.with(getApplicationContext())
                                .load(urlImage)
                                .into(portada);
                        nombreMotel.setText(motel.getNombre());
                        direccionMotel.setText(motel.getDireccion());
                    }
                    System.out.println(usuario.toString()+ "pene");

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
