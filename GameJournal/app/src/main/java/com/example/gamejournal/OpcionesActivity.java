package com.example.gamejournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OpcionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        TextView cerrarSesion = findViewById(R.id.textViewCerrarSesion);
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OpcionesActivity.this, "Sesión cerrada con éxito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OpcionesActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}