public class Ej_12 extends Thread{
    public int num;
    private static int[] numeros = new int[100];

    public Ej_12(int num){
        this.num = num;
    }

    public void run() {
        switch (num) {
            case 1:
                int suma = 0;
                for(int i=0; i<100; i++) {
                    suma = suma + numeros[i];
                }
                System.out.println("Suma: " + suma);
                break;
            case 2:
                double multi = 1;
                for(int i=0; i<100; i++){
                    multi = multi * numeros[i];
                }
                System.out.println("Multiplicación: " + multi);
                break;
            case 3:
                int max = 1;
                for (int i=0; i<100; i++){
                    max = Math.max(max,numeros[i]);
                }
                System.out.println("EL número mayor es: " + max);
                break;
            case 4:
                int min = 100;
                for (int i=0; i<100; i++){
                    min = Math.min(min,numeros[i]);
                }
                System.out.println("El múmero menor es: " + min);
                break;
        }
    }

    public static void main(String args[]) {
        //lleno el array y muestro los números
        for (int i=0; i<100; i++) {
            numeros[i] = (int) (Math.random()*100+1);
            System.out.print(numeros[i] + " ");
        }
        System.out.println();

        //instancio los hilos
        Ej_12 h1 = new Ej_12(1);
        Ej_12 h2 = new Ej_12(2);
        Ej_12 h3 = new Ej_12(3);
        Ej_12 h4 = new Ej_12(4);

        //inicializo los hilos
        h1.start();
        h2.start();
        h3.start();
        h4.start();

        try{
            h1.join();
            h2.join();
            h3.join();
            h4.join();
        }catch (InterruptedException e) {}

        System.out.println("\nEl programa ha finalizado");
    }
}
