package Ej_2;//VAMOS A HACER Q S3 ESTE EN EL MAIN
import java.util.Scanner;
public class Ej_2_2 extends Thread{
    private int num;
    private static int x,a,b,m1,m2,z,c,y,cuad;

    public Ej_2_2(int num) {
        this.num = num;
    }

    public void run(){
        cuad = x * x;
        System.out.println("S1: cuad = " + cuad);
        m1 = a * cuad;
        System.out.println("S2: m1 = " + m1);
    }

    public static void main(String args[]) {
        Scanner es = new Scanner(System.in);

        //introduzco el valor de las variables
        System.out.print("Introduce valor de x: ");
        x = es.nextInt();
        System.out.print("Introduce valor de a: ");
        a = es.nextInt();
        System.out.print("Introduce valor de b: ");
        b = es.nextInt();
        System.out.print("Introduce valor de c: ");
        c = es.nextInt();
        System.out.println();

        //instancio los hilos
        Ej_2_2 h1 = new Ej_2_2(1);

        //los dos primeros hilos
        h1.start();
            //el segundo hilo, q es concurrente con S1
        m2 = b * x;
        System.out.println("S3: m2 = " + m2);

        //paralizo la ejecucion hasta q ha finalizado h1
        try {
            h1.join();
        } catch (InterruptedException e) {}

        //el ultimo hilo
        z = m1 + m2;
        System.out.println("S4: z = " + z);
        y = z + c;
        System.out.println("S5: y = " + y);

        //mando un mensaje final
        System.out.println("\nEl programa ha finalizado");
    }
}
