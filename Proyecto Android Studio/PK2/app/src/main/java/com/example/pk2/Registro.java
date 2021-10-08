package com.example.pk2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class Registro extends AppCompatActivity {

    TextView correo;
    TextView contraseña;
    TextView nombre;
    TextView apellido;
    TextView cedula;
    //variable de la base de datos
    FirebaseAuth mAuth;
    //Base de datos
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getWindow().setStatusBarColor(getResources().getColor(R.color.moraitoMelo));
        //inflate base de datos y autenticacion
        mAuth =FirebaseAuth.getInstance();
        database = database.getReference();
        //inflate texts para obtener los datos
        correo = findViewById(R.id.inputMailRegi);
        contraseña = findViewById(R.id.inputPasswordRegi);
        nombre = findViewById(R.id.nombreInputRegi);
        apellido = findViewById(R.id.apellidoInputRegi);
        cedula = findViewById(R.id.cedulaInputRegi);
    }
    public void regist(View v)
    {
        String mail = correo.getText().toString();
        String pass = contraseña.getText().toString();
        String name = nombre.getText().toString();
        String lastN = apellido.getText().toString();
        String cc = cedula.getText().toString();
        if(validarDatos(mail,pass))
        {
            mAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if(user != null)
                        {
                            UserProfileChangeRequest.Builder upcrb = new UserProfileChangeRequest.Builder();
                            upcrb.setDisplayName(name + " " + lastN);
                        }
                    }else
                    {
                        //error al subir a la base de datos
                        Toast.makeText(Registro.this,"Error: "+task.getException().toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else
        {
            //dialogo para error en la contraseña o correo
            Toast.makeText(Registro.this,"contraseña o correo erroneo", Toast.LENGTH_LONG).show();
        }
    }
    private boolean validarDatos(String mail, String pass)
    {
        //validacion forma de correo y tamaño de contraseña correctos
        if(Patterns.EMAIL_ADDRESS.matcher(mail).matches() && pass.length()>=6) {
            return true;
        }else
            return false;
    }
}
