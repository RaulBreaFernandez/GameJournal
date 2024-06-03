package com.example.gamejournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CuentaActivity extends AppCompatActivity {

    private Boolean visibilidadContrasena = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);
        TextView contrasena = findViewById(R.id.contrasena);
        Button mostrarContrasena = findViewById(R.id.botonMostrarContrasena);
        contrasena.setTransformationMethod(new PasswordTransformationMethod());

        mostrarContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visibilidadContrasena) {
                    // Ocultar la contraseña
                    contrasena.setTransformationMethod(new PasswordTransformationMethod());
                    mostrarContrasena.setText("Mostrar Contraseña");
                } else {
                    // Mostrar la contraseña
                    contrasena.setTransformationMethod(new HideReturnsTransformationMethod());
                    mostrarContrasena.setText("Ocultar Contraseña");
                }
                visibilidadContrasena = !visibilidadContrasena;
            }
        });
    }
}