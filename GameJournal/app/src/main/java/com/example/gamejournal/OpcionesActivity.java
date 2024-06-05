package com.example.gamejournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OpcionesActivity extends AppCompatActivity {

    // Variable para controlar la visibilidad de la contraseña
    private Boolean visibilidadContrasena = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        // Asigna el TextView cerrarSesion a la vista correspondiente en el layout
        TextView cerrarSesion = findViewById(R.id.textViewCerrarSesion);
        // Configura un OnClickListener para el TextView cerrarSesion
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Muestra un mensaje toast indicando que la sesión se ha cerrado con éxito
                Toast.makeText(OpcionesActivity.this, "Sesión cerrada con éxito", Toast.LENGTH_SHORT).show();

                // Inicia la actividad IntroActivity
                Intent intent = new Intent(OpcionesActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });
        // Asigna el TextView contrasena a la vista correspondiente en el layout
        TextView contrasena = findViewById(R.id.contrasena);
        // Asigna el Button mostrarContrasena a la vista correspondiente en el layout
        Button mostrarContrasena = findViewById(R.id.botonMostrarContrasena);
        // Establece la transformación inicial del TextView contrasena para ocultar la contraseña
        contrasena.setTransformationMethod(new PasswordTransformationMethod());
        // Configura un OnClickListener para el Button mostrarContrasena
        mostrarContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visibilidadContrasena) {
                    // Si la contraseña está visible, ocúltala
                    contrasena.setTransformationMethod(new PasswordTransformationMethod());
                    mostrarContrasena.setText("Mostrar Contraseña");
                } else {
                    // Si la contraseña está oculta, muéstrala
                    contrasena.setTransformationMethod(new HideReturnsTransformationMethod());
                    mostrarContrasena.setText("Ocultar Contraseña");
                }
                // Alterna el estado de visibilidad de la contraseña
                visibilidadContrasena = !visibilidadContrasena;
            }
        });
    }
}
