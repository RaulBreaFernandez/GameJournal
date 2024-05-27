package com.example.gamejournal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class VideojuegoDetailActivity extends AppCompatActivity {

    private TextView tituloVideojuego, puntuacionVideojuego,textoResumen, anoVideojuego, desarrolladora;
    private ImageView imageViewJuego, flechaAtras, botonCorazon;
    private Videojuego videojuego;
    private NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videojuego_detail);

        videojuego = (Videojuego) getIntent().getSerializableExtra("videojuego");
        if (videojuego != null) {
            initView();
            loadVideojuegoData();
        }
    }

    private void initView() {
        tituloVideojuego = findViewById(R.id.tituloVideojuego);
        scrollView = findViewById(R.id.scrollView);
        textoResumen = findViewById(R.id.textoResumen);
        imageViewJuego = findViewById(R.id.imageViewJuegoDetalle);
        puntuacionVideojuego = findViewById(R.id.textViewPuntuacion);
        flechaAtras = findViewById(R.id.flechaAtras);
        botonCorazon = findViewById(R.id.corazon);
        desarrolladora = findViewById(R.id.desarrolladora);
        anoVideojuego = findViewById(R.id.anoLanzamientoNumero);
        flechaAtras.setOnClickListener(v -> finish());
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
}