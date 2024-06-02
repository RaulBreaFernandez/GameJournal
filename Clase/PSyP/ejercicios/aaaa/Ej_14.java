public class Ej_14 extends Thread{
    private String nombre;

    public Ej_14(String nombre) {
        this.nombre = nombre;
    }

    public void run() {
        System.out.println("Azrael atrapa a " + nombre);
    }

    public static void main(String args[]) {

        String[] nombres = {"Papa Pitufo", "Pitufina", "Pintor", "Gruñón",
                "Bromista", "Filosofo", "Tímido"};

        for(String nom : nombres) {
            Ej_14 e = new Ej_14 (nom);
            e.start();
        }
    }
}
