package com.example.gamejournal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class VideojuegoDetailActivity extends AppCompatActivity {

    private TextView tituloVideojuego, puntuacionVideojuego,textoResumen, anoVideojuego, desarrolladora;
    private ImageView imageViewJuego, flechaAtras, corazon;
    private Videojuego videojuego;
    private SharedPreferences sharedPreferences;
    private CheckBox jugado, loQuiero, loRecomiendo;
    boolean favorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videojuego_detail);
        sharedPreferences = getSharedPreferences("MisPrefs", Context.MODE_PRIVATE);

        videojuego = (Videojuego) getIntent().getSerializableExtra("videojuego");
        if (videojuego != null) {
            initView();
            loadVideojuegoData();
        }

        corazon = findViewById(R.id.Corazon);
        // Establece un clic Listener para el corazón
        corazon.setOnClickListener(v -> {
            // Cambia el color del corazón y muestra un Toast
            if (favorito) {
                corazon.setImageResource(R.drawable.corazon); // Cambia a la imagen de corazón blanco
                favorito = false;
                Toast.makeText(getApplicationContext(), "Juego eliminado de favoritos", Toast.LENGTH_SHORT).show();
            } else {
                corazon.setImageResource(R.drawable.corazon_rojo); // Cambia a la imagen de corazón rojo
                favorito = true;
                Toast.makeText(getApplicationContext(), "Juego añadido a favoritos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        String gameId = videojuego.getTitulo();
        tituloVideojuego = findViewById(R.id.tituloVideojuego);
        textoResumen = findViewById(R.id.textoResumen);
        imageViewJuego = findViewById(R.id.imageViewJuegoDetalle);
        puntuacionVideojuego = findViewById(R.id.textViewPuntuacion);
        flechaAtras = findViewById(R.id.flechaAtras);
        desarrolladora = findViewById(R.id.desarrolladora);
        anoVideojuego = findViewById(R.id.anoLanzamientoNumero);
        jugado = findViewById(R.id.checkboxJugado);
        loQuiero = findViewById(R.id.checkBoxQuiero);
        loRecomiendo = findViewById(R.id.checkBoxRecomendado);
        flechaAtras.setOnClickListener(v -> finish());
        corazon = findViewById(R.id.corazon);
        jugado.setChecked(sharedPreferences.getBoolean(gameId + "_jugado", false));
        loRecomiendo.setChecked(sharedPreferences.getBoolean(gameId + "_recomendado", false));
        // Asigna un listener a cada CheckBox para guardar su estado en SharedPreferences cuando cambien
        jugado.setOnCheckedChangeListener((buttonView, isChecked) -> saveCheckBoxState(gameId + "_jugado", isChecked));
        loRecomiendo.setOnCheckedChangeListener((buttonView, isChecked) -> saveCheckBoxState(gameId + "_recomendado", isChecked));
        loQuiero.setOnCheckedChangeListener((buttonView, isChecked) -> saveCheckBoxState(gameId + "_quiero", isChecked));
        favorito = sharedPreferences.getBoolean(videojuego.getTitulo() + "_favorito", false);
    }

    private void loadVideojuegoData() {
        tituloVideojuego.setText(videojuego.getTitulo());
        textoResumen.setText(videojuego.getSinopsis());
        anoVideojuego.setText(String.valueOf(videojuego.getAno()));
        desarrolladora.setText(videojuego.getDesarrolladora());
        puntuacionVideojuego.setText(String.valueOf(videojuego.getPuntuacion()));
        Glide.with(this)
                .load(videojuego.getUrlPosterDetalle())
                .into(imageViewJuego);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String gameId = videojuego.getTitulo(); // Obtener el identificador único del juego
        // Restaurar el estado de los CheckBox del juego actual
        jugado.setChecked(sharedPreferences.getBoolean(gameId + "_jugado", false));
        loRecomiendo.setChecked(sharedPreferences.getBoolean(gameId + "_recomendado", false));
        loQuiero.setChecked(sharedPreferences.getBoolean(gameId + "_quiero", false));

        // Restaurar el estado de favorito
        favorito = sharedPreferences.getBoolean(gameId + "_favorito", false);
        // Actualizar la imagen del corazón
        if (favorito) {
            corazon.setImageResource(R.drawable.corazon_rojo); // Cambia a la imagen de corazón rojo
        } else {
            corazon.setImageResource(R.drawable.corazon); // Cambia a la imagen de corazón blanco
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Guardar el estado de favorito al salir de la actividad
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(videojuego.getTitulo() + "_favorito", favorito);
        editor.apply();
    }

    protected void isChecked() {
        String gameId = videojuego.getTitulo(); // Utiliza el título del juego como identificador único
        // Restaurar el estado de los CheckBox
        jugado.setChecked(sharedPreferences.getBoolean(gameId + "_jugado", false));
        loRecomiendo.setChecked(sharedPreferences.getBoolean(gameId + "_recomendado", false));
        loQuiero.setChecked(sharedPreferences.getBoolean(gameId + "_quiero", false));
        // Listener para guardar el estado de los CheckBox cuando cambian
        jugado.setOnCheckedChangeListener((buttonView, isChecked) -> saveCheckBoxState(gameId + "_jugado", isChecked));
        loRecomiendo.setOnCheckedChangeListener((buttonView, isChecked) -> saveCheckBoxState(gameId + "_recomendado", isChecked));
        loQuiero.setOnCheckedChangeListener((buttonView, isChecked) -> saveCheckBoxState(gameId + "_quiero", isChecked));
    }

    // Método para guardar el estado de un CheckBox en SharedPreferences
    private void saveCheckBoxState(String key, boolean isChecked) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, isChecked);
        editor.apply();
    }
}