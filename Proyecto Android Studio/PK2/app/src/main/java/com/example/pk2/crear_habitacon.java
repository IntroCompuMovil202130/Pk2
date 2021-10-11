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
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Para la creación de habitación.
public class crear_habitacon extends AppCompatActivity {
    private static final int RCODE_CAMERA = 1, RCODE_GALLERY = 2;
    Bitmap imageArray[];
    EditText etxt_NombreHabitacion, etext_Precio, etext_Descripcion;
    ImageButton img_1, img_2, img_3, img_4, img_5, img_6;
    Button btn_gallery, btn_camara, btn_save;
    int imgIndexClicked = -1;
    //Base de datos
    FirebaseDatabase database;
    DatabaseReference myRef;
    static final String PATH_HABITACION = "habitacion/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_habitacon);
        //Inflación de los editText:
        etext_Descripcion = findViewById(R.id.DescripcionHabitacion);
        etext_Precio = findViewById(R.id.inputMotelAdd);
        etxt_NombreHabitacion = findViewById(R.id.inputMotelNom);
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

    }
    public void clickedOnImg1(View v)
    {
        applySelectColor(imgIndexClicked, 0);
        imgIndexClicked = 0;
    }
    public void clickedOnImg2(View v)
    {
        applySelectColor(imgIndexClicked, 1);
        imgIndexClicked = 1;
    }
    public void clickedOnImg3(View v)
    {
        applySelectColor(imgIndexClicked, 2);
        imgIndexClicked = 2;
    }
    public void clickedOnImg4(View v)
    {
        applySelectColor(imgIndexClicked, 3);
        imgIndexClicked = 3;
    }
    public void clickedOnImg5(View v)
    {
        applySelectColor(imgIndexClicked, 4);
        imgIndexClicked = 4;
    }
    public void clickedOnImg6(View v)
    {
        applySelectColor(imgIndexClicked, 5);
        imgIndexClicked = 5;
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
                //TODO Mostrar justificacion
            }
            ActivityCompat.requestPermissions(context, new String[]{permisos}, id_Code);
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
            case RCODE_GALLERY:
                if (grantResults.length>0
                        && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    //TODO - Execute Gallery Functionality
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
                    //TODO Save image in array and put it on screen.
                }
                break;
        }
    }

    private void applySelectColor (int prevIMG, int currIMG)
    {
        switch (currIMG)
        {
            case 0:
                img_1.getBackground().setColorFilter(Color.parseColor("#FFFFEB3B"),
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
}
