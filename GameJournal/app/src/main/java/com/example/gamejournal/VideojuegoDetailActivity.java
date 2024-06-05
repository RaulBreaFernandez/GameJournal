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

    // Declaración de variables de la interfaz de usuario y otros elementos
    private TextView tituloVideojuego, puntuacionVideojuego, textoResumen, anoVideojuego, desarrolladora;
    private ImageView imageViewJuego, flechaAtras, corazon;
    private Videojuego videojuego;
    private SharedPreferences sharedPreferences;
    private CheckBox jugado, loQuiero, loRecomiendo;
    boolean favorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videojuego_detail);
        // Inicialización de SharedPreferences
        sharedPreferences = getSharedPreferences("MisPrefs", Context.MODE_PRIVATE);
        // Obtención del objeto Videojuego pasado a través del Intent
        videojuego = (Videojuego) getIntent().getSerializableExtra("videojuego");
        if (videojuego != null) {
            initView();
            loadVideojuegoData();
        }
        // Inicialización del ImageView corazon
        corazon = findViewById(R.id.Corazon);
        // Establece un listener para manejar el clic en el corazón
        corazon.setOnClickListener(v -> {
            // Alterna el estado de favorito y actualiza la interfaz de usuario y muestra un Toast
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
    // Método para inicializar las vistas y configurar los listeners
    private void initView() {
        String gameId = videojuego.getTitulo(); // Utiliza el título del juego como identificador único
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
        // Listener para el botón de retroceso
        flechaAtras.setOnClickListener(v -> finish());
        // Inicialización del estado de los CheckBox desde SharedPreferences
        jugado.setChecked(sharedPreferences.getBoolean(gameId + "_jugado", false));
        loRecomiendo.setChecked(sharedPreferences.getBoolean(gameId + "_recomendado", false));
        // Listeners para guardar el estado de los CheckBox en SharedPreferences cuando cambien
        jugado.setOnCheckedChangeListener((buttonView, isChecked) -> saveCheckBoxState(gameId + "_jugado", isChecked));
        loRecomiendo.setOnCheckedChangeListener((buttonView, isChecked) -> saveCheckBoxState(gameId + "_recomendado", isChecked));
        loQuiero.setOnCheckedChangeListener((buttonView, isChecked) -> saveCheckBoxState(gameId + "_quiero", isChecked));
        // Inicialización del estado de favorito desde SharedPreferences
        favorito = sharedPreferences.getBoolean(videojuego.getTitulo() + "_favorito", false);
    }

    // Método para cargar los datos del videojuego en las vistas correspondientes
    private void loadVideojuegoData() {
        tituloVideojuego.setText(videojuego.getTitulo());
        textoResumen.setText(videojuego.getSinopsis());
        anoVideojuego.setText(String.valueOf(videojuego.getAno()));
        desarrolladora.setText(videojuego.getDesarrolladora());
        puntuacionVideojuego.setText(String.valueOf(videojuego.getPuntuacion()));
        // Uso de Glide para cargar la imagen del videojuego en el ImageView
        Glide.with(this)
                .load(videojuego.getUrlPosterDetalle())
                .into(imageViewJuego);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String gameId = videojuego.getTitulo(); // Obtener el identificador único del juego
        // Restaurar el estado de los CheckBox del juego actual desde SharedPreferences
        jugado.setChecked(sharedPreferences.getBoolean(gameId + "_jugado", false));
        loRecomiendo.setChecked(sharedPreferences.getBoolean(gameId + "_recomendado", false));
        loQuiero.setChecked(sharedPreferences.getBoolean(gameId + "_quiero", false));
        // Restaurar el estado de favorito desde SharedPreferences
        favorito = sharedPreferences.getBoolean(gameId + "_favorito", false);
        // Actualizar la imagen del corazón según el estado de favorito
        if (favorito) {
            corazon.setImageResource(R.drawable.corazon_rojo); // Cambia a la imagen de corazón rojo
        } else {
            corazon.setImageResource(R.drawable.corazon); // Cambia a la imagen de corazón blanco
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Guardar el estado de favorito en SharedPreferences al salir de la actividad
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(videojuego.getTitulo() + "_favorito", favorito);
        editor.apply();
    }

    // Método para restaurar el estado de los CheckBox
    protected void isChecked() {
        String gameId = videojuego.getTitulo(); // Utiliza el título del juego como identificador único
        // Restaurar el estado de los CheckBox desde SharedPreferences
        jugado.setChecked(sharedPreferences.getBoolean(gameId + "_jugado", false));
        loRecomiendo.setChecked(sharedPreferences.getBoolean(gameId + "_recomendado", false));
        loQuiero.setChecked(sharedPreferences.getBoolean(gameId + "_quiero", false));
        // Listeners para guardar el estado de los CheckBox en SharedPreferences cuando cambien
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
