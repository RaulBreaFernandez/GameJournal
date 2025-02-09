package com.example.gamejournal;

import java.io.Serializable;

public class Videojuego implements Serializable {

    private String titulo, genero, desarrolladora, urlPoster, sinopsis, urlPosterDetalle;
    private int ano;
    private double puntuacion;
    boolean favorito;

    public Videojuego(String titulo, String genero, String desarrolladora, int ano, String urlPoster, String sinopsis, double puntuacion, String urlPosterDetalle, Boolean favorito) {
        this.titulo = titulo;
        this.genero = genero;
        this.desarrolladora = desarrolladora;
        this.ano = ano;
        this.urlPoster = urlPoster;
        this.sinopsis = sinopsis;
        this.puntuacion = puntuacion;
        this.urlPosterDetalle = urlPosterDetalle;
        this.favorito = favorito;
    }

    public String getUrlPosterDetalle() { return urlPosterDetalle; }

    public double getPuntuacion() { return puntuacion; }

    public String getSinopsis() { return sinopsis; }

    public String getTitulo() { return titulo; }

    public String getUrlPoster() { return urlPoster; }

    public String getGenero() { return genero; }

    public String getDesarrolladora() { return desarrolladora; }

    public int getAno() { return ano; }

    public boolean isFavorito() { return favorito; }

    public void setFavorito(boolean favorito) { this.favorito = favorito; }
}
