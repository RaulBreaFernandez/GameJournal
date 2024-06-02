package com.example.gamejournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText userEdit, passEdit;
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView() {

        userEdit = findViewById(R.id.editTextUsername);
        passEdit = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userEdit.getText().toString().isEmpty() || passEdit.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Introduce tu nombre de usuario y contraseña", Toast.LENGTH_SHORT).show();
                } else if (userEdit.getText().toString().equals("test") && passEdit.getText().toString().equals("test")) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Nombre de usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}