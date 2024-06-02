package Ej_13;

public class Cliente {
    private String nombre;
    private int[] tiempos;

    public Cliente(String nombre, int tiempos[]){
        this.nombre = nombre;
        this.tiempos = tiempos;
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getTiempos() {
        return tiempos;
    }
}

