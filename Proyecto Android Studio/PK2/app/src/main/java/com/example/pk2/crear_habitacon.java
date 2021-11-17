package com.example.pk2;

import androidx.activity.result.contract.ActivityResultContracts;
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
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pk2.model.Habitacion;
import com.example.pk2.model.Motel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.InputStream;

//Para la creación de habitación.
public class crear_habitacon extends AppCompatActivity {
    private static final int RCODE_CAMERA = 1, RCODE_REXTERNAL = 2, RCODE_WEXTERNAL = 3;
    Bitmap imageArray[];
    EditText etxt_NombreHabitacion, etext_Precio, etext_Descripcion;
    ImageButton img_1, img_2, img_3, img_4, img_5, img_6;
    Button btn_gallery, btn_camara, btn_save;
    int imgIndexClicked = -1;
    //Base de datos
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    static final String PATH_HABITACION = "motel/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_habitacon);
        //Inflación de los editText:
        etext_Descripcion = findViewById(R.id.DescripcionHabitacion);
        etext_Precio = findViewById(R.id.inputMotelAdd);
        etxt_NombreHabitacion = findViewById(R.id.inputMotelNom);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        //Inflación de los Botones de Imagen:
        img_1 = findViewById(R.id.img_1);
        img_2 = findViewById(R.id.img_2);
        img_3 = findViewById(R.id.img_3);
        img_4 = findViewById(R.id.img_4);
        img_5 = findViewById(R.id.img_5);
        img_6 = findViewById(R.id.img_6);
        //Inflación de Botones:
        btn_gallery = findViewById(R.id.botonAbrirGaleria);
        btn_camara = findViewById(R.id.botonTomarFoto);
        btn_save = findViewById(R.id.botonRegistrarse);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regist(v);
            }
        });
        imageArray = new Bitmap[6];

    }
    public void regist(View v)
    {
        String nom = "habitacion del placer";
        if(!nom.isEmpty()) {
            Habitacion habitacion = new Habitacion(nom);
            myRef = database.getReference(  PATH_HABITACION + mAuth.getCurrentUser().getUid()  + "/habitacion/" + "1");
            myRef.setValue(habitacion);
        }

    }
    public void clickedOnImg1(View v)
    {
        if (imgIndexClicked == 0)
        {
            applySelectColor(imgIndexClicked, -1);
            imgIndexClicked = -1;
        }
        else {
            applySelectColor(imgIndexClicked, 0);
            imgIndexClicked = 0;
        }
    }
    public void clickedOnImg2(View v)
    {
        if (imgIndexClicked == 1)
        {
            applySelectColor(imgIndexClicked, -1);
            imgIndexClicked = -1;
        }
        else {
            applySelectColor(imgIndexClicked, 1);
            imgIndexClicked = 1;
        }
    }
    public void clickedOnImg3(View v)
    {
        if (imgIndexClicked == 2)
        {
            applySelectColor(imgIndexClicked, -1);
            imgIndexClicked = -1;
        }
        else {
            applySelectColor(imgIndexClicked, 2);
            imgIndexClicked = 2;
        }
    }
    public void clickedOnImg4(View v)
    {
        if (imgIndexClicked == 3)
        {
            applySelectColor(imgIndexClicked, -1);
            imgIndexClicked = -1;
        }
        else {
            applySelectColor(imgIndexClicked, 3);
            imgIndexClicked = 3;
        }
    }
    public void clickedOnImg5(View v)
    {
        if (imgIndexClicked == 4)
        {
            applySelectColor(imgIndexClicked, -1);
            imgIndexClicked = -1;
        }
        else {
            applySelectColor(imgIndexClicked, 4);
            imgIndexClicked = 4;
        }
    }
    public void clickedOnImg6(View v)
    {
        if (imgIndexClicked == 5)
        {
            applySelectColor(imgIndexClicked, -1);
            imgIndexClicked = -1;
        }
        else {
            applySelectColor(imgIndexClicked, 5);
            imgIndexClicked = 5;
        }
    }
    public void onClickPhotoManager(View v)
    {
        if (imgIndexClicked != -1)
        {
            //Write external storage
            verificarPermiso(this, Manifest.permission.WRITE_EXTERNAL_STORAGE,"Para usar la camara, es necesario este permiso!",
                    RCODE_WEXTERNAL);
        }
        else
        {
            Toast.makeText(crear_habitacon.this, "Por favor seleccionar una imagen...!",Toast.LENGTH_LONG).show();
        }


    }

    public void onClickGalleryManager(View v)
    {
        if (imgIndexClicked != -1)
        {
            //Write external storage
            verificarPermiso(this, Manifest.permission.READ_EXTERNAL_STORAGE,"Para usar la galería, es necesario aceptar el permiso!",
                    RCODE_REXTERNAL);
        }
        else
        {
            Toast.makeText(crear_habitacon.this, "Por favor seleccionar una imagen...!",Toast.LENGTH_LONG).show();
        }
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
                Toast.makeText(crear_habitacon.this, justificacion,Toast.LENGTH_LONG).show();
            }
            ActivityCompat.requestPermissions(context, new String[]{permisos}, id_Code);
        }
        else
        {
            itHasPermission(id_Code);
        }

    }
    private void itHasPermission (int id_Code)
    {
        if (id_Code == RCODE_WEXTERNAL)
        {
            verificarPermiso(this, Manifest.permission.CAMERA,
                    "\"Para usar la camara, es necesario este permiso!\"",RCODE_CAMERA);
        }
        else if (id_Code == RCODE_CAMERA)
        {
            tomarFoto();
        }
        else if (id_Code == RCODE_REXTERNAL)
        {
            abrirGaleria();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],int[]grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case RCODE_CAMERA:
                if (grantResults.length>0
                        && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    tomarFoto();
                }
                else
                {
                    //TODO - Deny Permission
                }
                break;
            case RCODE_REXTERNAL:
                if (grantResults.length>0
                        && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    abrirGaleria();
                }
                else
                {
                    //TODO - Deny Permission
                }
                break;
            case RCODE_WEXTERNAL:
                if (grantResults.length>0
                        && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    verificarPermiso(this, Manifest.permission.CAMERA,
                            "\"Para usar la camara, es necesario este permiso!\"",RCODE_CAMERA); //Permisos de camara...!
                }
                else
                {
                    //TODO - Deny Permission
                }
                break;

        }

    }

    private void tomarFoto()
    {
        Intent tomarFotoIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        try
        {
            startActivityForResult(tomarFotoIntent, RCODE_CAMERA);
        }
        catch (ActivityNotFoundException e)
        {
            Log.e("PERMISSION_APP", e.getMessage());
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
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case RCODE_CAMERA:
                if (resultCode == RESULT_OK )
                {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageArray[imgIndexClicked] = imageBitmap;
                    changeImageOnScreen();
                }
                break;
            case RCODE_REXTERNAL:
                if (resultCode == RESULT_OK)
                {
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap myImage = BitmapFactory.decodeStream(imageStream);
                        imageArray[imgIndexClicked] = myImage;
                        changeImageOnScreen();
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
        }
    }

    private void applySelectColor (int prevIMG, int currIMG)
    {
        switch (currIMG)
        {
            case 0:
                img_1.getBackground().setColorFilter(Color.parseColor("#FFFFFFFF"),
                        PorterDuff.Mode.SRC_ATOP);
                break;
            case 1:
                img_2.getBackground().setColorFilter(Color.parseColor("#FFFFEB3B"),
                        PorterDuff.Mode.SRC_ATOP);
                break;
            case 2:
                img_3.getBackground().setColorFilter(Color.parseColor("#FFFFEB3B"),
                        PorterDuff.Mode.SRC_ATOP);
                break;
            case 3:
                img_4.getBackground().setColorFilter(Color.parseColor("#FFFFEB3B"),
                        PorterDuff.Mode.SRC_ATOP);
                break;
            case 4:
                img_5.getBackground().setColorFilter(Color.parseColor("#FFFFEB3B"),
                        PorterDuff.Mode.SRC_ATOP);
                break;
            case 5:
                img_6.getBackground().setColorFilter(Color.parseColor("#FFFFEB3B"),
                        PorterDuff.Mode.SRC_ATOP);
                break;
            default:
                break;

        }
        switch (prevIMG)
        {
            case 0:
                img_1.getBackground().setColorFilter(Color.parseColor("#FFDDDDDD"),
                        PorterDuff.Mode.SRC_ATOP);
                break;
            case 1:
                img_2.getBackground().setColorFilter(Color.parseColor("#FFDDDDDD"),
                        PorterDuff.Mode.SRC_ATOP);
                break;
            case 2:
                img_3.getBackground().setColorFilter(Color.parseColor("#FFDDDDDD"),
                        PorterDuff.Mode.SRC_ATOP);
                break;
            case 3:
                img_4.getBackground().setColorFilter(Color.parseColor("#FFDDDDDD"),
                        PorterDuff.Mode.SRC_ATOP);
                break;
            case 4:
                img_5.getBackground().setColorFilter(Color.parseColor("#FFDDDDDD"),
                        PorterDuff.Mode.SRC_ATOP);
                break;
            case 5:
                img_6.getBackground().setColorFilter(Color.parseColor("#FFDDDDDD"),
                        PorterDuff.Mode.SRC_ATOP);
                break;
            default:
                break;
        }

    }
    private void changeImageOnScreen()
    {
        switch (imgIndexClicked)
        {
            case 0:
                img_1.setImageBitmap(imageArray[imgIndexClicked]);
                break;
            case 1:
                img_2.setImageBitmap(imageArray[imgIndexClicked]);
                break;
            case 2:
                img_3.setImageBitmap(imageArray[imgIndexClicked]);
                break;
            case 3:
                img_4.setImageBitmap(imageArray[imgIndexClicked]);
                break;
            case 4:
                img_5.setImageBitmap(imageArray[imgIndexClicked]);
                break;
            case 5:
                img_6.setImageBitmap(imageArray[imgIndexClicked]);
                break;
        }
    }
}
