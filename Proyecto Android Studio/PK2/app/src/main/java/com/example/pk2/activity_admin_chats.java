package com.example.pk2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import com.example.pk2.model.ChatElementoList;
import com.example.pk2.model.MotelElementoList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class activity_admin_chats extends AppCompatActivity {

    static final String USER_PATH = "users/";
    static final String DUENO_PATH = "dueno/";
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    List<ChatElementoList> elementos;

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_chats);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().getWindowInsetsController().setSystemBarsAppearance(0x00000008, 0x00000008);
        //inflate
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        elementos = new ArrayList<>();

    }

}