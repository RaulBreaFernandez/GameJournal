package Ej_19;

public class Saludo {
    public synchronized void getRespuestaAlumno(String nombre){
        try {
            wait();
            System.out.println(nombre + " --> Buenos días profesora");
        }catch (InterruptedException e) {}
    }

    public synchronized void getSaludoProfesor() {
        notifyAll();
        System.out.println("Profesora --> Buenos días");;
    }
}
