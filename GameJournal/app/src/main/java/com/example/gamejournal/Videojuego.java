package com.example.gamejournal;

public class Videojuego {

    private String titulo, genero, desarrolladora, urlPoster;
    private int ano;

    public Videojuego(String titulo, String genero, String desarrolladora, int ano, String urlPoster) {
        this.titulo = titulo;
        this.genero = genero;
        this.desarrolladora = desarrolladora;
        this.ano = ano;
        this.urlPoster = urlPoster;
    }

    public String getTitulo() { return titulo; }

    public String getUrlPoster() { return urlPoster; }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }

    public void setGenero(String genero) { this.genero = genero; }

    public String getDesarrolladora() { return desarrolladora; }

    public void setDesarrolladora(String desarrolladora) { this.desarrolladora = desarrolladora; }

    public int getAno() { return ano; }

    public void setAno(int ano) { this.ano = ano; }
}
