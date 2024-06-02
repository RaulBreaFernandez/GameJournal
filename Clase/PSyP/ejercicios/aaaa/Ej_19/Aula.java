package Ej_19;

public class Aula extends Thread{
    private Saludo saludo;
    private String nombre;
    private boolean profesor;

    public Aula(Saludo s, String n, boolean p) {
        saludo = s;
        nombre = n;
        profesor = p;
    }

    public void run(){
        System.out.println("\t(" + nombre + " entra al aula)");
        if (profesor)
            saludo.getSaludoProfesor();
        else
            saludo.getRespuestaAlumno(nombre);
    }

    public static void main(String[] args) {
        Saludo hola = new Saludo();
        Aula profesora = new Aula(hola,"Profesora", true);
        Aula pepito = new Aula(hola,"Pepito", false);
        Aula jaimito = new Aula(hola,"Jaimito", false);
        Aula jorgito = new Aula(hola,"Jorgito", false);
        Aula carlitos = new Aula(hola,"Carlitos", false);
        Aula juanito = new Aula(hola,"Juanito", false);
        Aula juanita = new Aula(hola,"Juanita", false);

        profesora.start();
        pepito.start();
        jaimito.start();
        jorgito.start();
        carlitos.start();
        juanito.start();
        juanita.start();

        try{
            profesora.join();
            pepito.join();
            jaimito.join();
            jorgito.join();
            carlitos.join();
            juanita.join();
            juanito.join();
        }catch (InterruptedException e){}
    }
}
