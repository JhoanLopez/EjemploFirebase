package com.jhoanlopez.ejemplofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button butRegistrar;
    Button butIniciar;
    EditText edtUser;
    EditText edtPasswd;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser  user = mAuth.getCurrentUser();

        if (user != null) {
            Intent toHome = new Intent(MainActivity.this, HomeActivity.class);
            toHome.putExtra("EMAIL", user.getEmail());
            startActivity(toHome);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUser = findViewById(R.id.user);
        edtPasswd = findViewById(R.id.passwd);
        butRegistrar = findViewById(R.id.registrarse);
        butIniciar = findViewById(R.id.inicio);


        butRegistrar.setOnClickListener(v -> registro(v));
        butIniciar.setOnClickListener(v -> login(v));
    }


    public void login (View v) {
        String email = edtUser.getText().toString();
        String contraseña = edtPasswd.getText().toString();

        if (email == null || email.isEmpty()) {
            Toast.makeText(this, "Debes introducir un Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (contraseña == null || contraseña.isEmpty()) {
            Toast.makeText(this, "Debes introducir una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent toHome = new Intent(MainActivity.this, HomeActivity.class);
                    toHome.putExtra("EMAIL", email);
                    startActivity(toHome);

                } else {
                    Toast.makeText(MainActivity.this, "Error al iniciar sesión"  , Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void registro (View v) {
        String email = edtUser.getText().toString();
        String contraseña = edtPasswd.getText().toString();

        if (email == null || email.isEmpty()) {
            Toast.makeText(this, "Debes introducir un Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (contraseña == null || contraseña.isEmpty()) {
            Toast.makeText(this, "Debes introducir una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent toHome = new Intent(MainActivity.this, HomeActivity.class);
                    toHome.putExtra("EMAIL", email);
                    startActivity(toHome);

                } else {
                    Toast.makeText(MainActivity.this, "Error al crear la cuenta"  , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}