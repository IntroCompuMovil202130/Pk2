package com.example.pk2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pk2.model.Motel;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class crear_motel extends AppCompatActivity {

    TextView nombre;
    TextView direccion;
    ImageView imagenMotel;
    //Base de datos
    FirebaseDatabase database;
    DatabaseReference myRef;
    static final String PATH_MOTEL = "motel/";
    private static final int RCODE_REXTERNAL = 2;
    Bitmap currShownImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_motel);
        nombre = findViewById(R.id.inputMotelNom);
        direccion = findViewById(R.id.inputMotelAdd);
        database = FirebaseDatabase.getInstance();
        imagenMotel = findViewById(R.id.ImagenMotel);
    }
    public void regist(View v)
    {
        String nom = nombre.getText().toString();
        String add = direccion.getText().toString();
        String id = getIntent().getStringExtra("cedula");
        if(!nom.isEmpty() && !add.isEmpty()) {
            Motel motel = new Motel();
            motel.setNombre(nom);
            motel.setDireccion(add);
            motel.setId(id);
            myRef = database.getReference(PATH_MOTEL);
            //asignacion de cc como key
            myRef = database.getReference(PATH_MOTEL + id);
            //escritura
            myRef.setValue(motel);
            Intent intent = new Intent(crear_motel.this,LogIn.class);
            startActivity(intent);
        }

    }

    public void onClickGalleryManagerMotel(View v)
    {

        //Write external storage
        verificarPermiso(this, Manifest.permission.READ_EXTERNAL_STORAGE,"Para usar la galería, es necesario aceptar el permiso!",
                    RCODE_REXTERNAL);


    }
    private void verificarPermiso(Activity context, String permisos, String justificacion,
                                  int id_Code)
    {
        if ((ContextCompat.checkSelfPermission(context,
                permisos) != PackageManager.PERMISSION_GRANTED))
        {
            //En caso que no se haya aceptado el permiso
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, permisos))
            {
                Toast.makeText(crear_motel.this, justificacion,Toast.LENGTH_LONG).show();
            }
            ActivityCompat.requestPermissions(context, new String[]{permisos}, id_Code);
        }
        else
        {
            abrirGaleria();
        }

    }
    private void abrirGaleria()
    {
        Intent i_galeria = new Intent(Intent.ACTION_PICK);

        i_galeria.setType("image/*");
        try {
            startActivityForResult(i_galeria, RCODE_REXTERNAL);
        }
        catch (ActivityNotFoundException e)
        {
            Log.e("PERMISSION_APP", e.getMessage());
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],int[]grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case RCODE_REXTERNAL:
                if (grantResults.length>0
                        && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    abrirGaleria();
                }
                else
                {
                    Toast.makeText(crear_motel.this, "La aplicación no tine el permiso apropiado para abrir la galería.",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap myImage = BitmapFactory.decodeStream(imageStream);
                currShownImage = myImage;
                imagenMotel.setImageBitmap(currShownImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
