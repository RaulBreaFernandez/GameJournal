package Ej_15;

public class Raton extends Thread{
    private String name;
    private int time;

    public Raton(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public void run(){
        //imprimo la entrada
        System.out.println("El ratón " + this.name + " ha comenzado a comer");

        //este metodo es para parar la ejecucion la cantidad de tiempo establecida *1000 y asi se ejecuta antes es q menos tarde en comer
        try {
            Thread.sleep(this.time * 1000); //se multiplica *1000 porq esta en milisegundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //imprimo la salida
        System.out.println("El ratón " + this.name + " ha terminado de comer.");
    }

    public static void main(String args[]) {
        Raton zipi = new Raton("Zipi", 4);
        Raton zape = new Raton("Zape", 5);
        Raton nai = new Raton("Nai", 6);
        Raton juno = new Raton("Juno", 3);

        zipi.start();
        zape.start();
        nai.start();
        juno.start();
    }
}
