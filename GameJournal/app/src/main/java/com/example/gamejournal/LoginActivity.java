package com.example.gamejournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Actividad de inicio de sesión para la aplicación.
public class LoginActivity extends AppCompatActivity {
    // Definición de variables para los campos de texto de usuario y contraseña, y el botón de inicio de sesión.
    private EditText userEdit, passEdit;
    private Button loginButton;
    // Método onCreate que se ejecuta cuando se crea la actividad.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establece el layout de la actividad.
        setContentView(R.layout.activity_login);
        // Inicializa las vistas.
        initView();
    }
    // Método para inicializar las vistas y establecer el listener del botón.
    private void initView() {
        // Asocia las variables con los elementos del layout.
        userEdit = findViewById(R.id.editTextUsername);
        passEdit = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        // Establece un listener de clic para el botón de inicio de sesión.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifica si los campos de usuario y contraseña están vacíos.
                if (userEdit.getText().toString().isEmpty() || passEdit.getText().toString().isEmpty()) {
                    // Muestra un mensaje de error si algún campo está vacío.
                    Toast.makeText(LoginActivity.this, "Introduce tu nombre de usuario y contraseña", Toast.LENGTH_SHORT).show();
                } else if (userEdit.getText().toString().equals("test") && passEdit.getText().toString().equals("test")) {
                    // Si el nombre de usuario y la contraseña son correctos, inicia la MainActivity.
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    // Si el nombre de usuario o la contraseña son incorrectos, muestra un mensaje de error.
                    Toast.makeText(LoginActivity.this, "Nombre de usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
