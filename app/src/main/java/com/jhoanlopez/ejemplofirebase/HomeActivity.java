package com.jhoanlopez.ejemplofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    Button desconectar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        desconectar = findViewById(R.id.desconectar);

        setTitle("Bienvenid@ " + getIntent().getExtras().getString("EMAIL"));

        desconectar.setOnClickListener(v -> desconectarMetodo());
    }

    private void desconectarMetodo() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, MainActivity.class));
    }
}