package Ej_10;

import java.util.Scanner;
public class Ej_10_No extends Thread{
    private int num;
    private static int a,m,n,y,z,x,b,c;

    public Ej_10_No(int num) {
        this.num = num;
    }

    public void run() {
        switch (num) {
            case 1:
                a = x*x;
                System.out.println("S1: a = " + a);
                m = a*b;
                System.out.println("S2: m = " + m);
                break;
            case 2:
                y = m+n;
                System.out.println("S4: y = " + y);
        }
    }

    public static void main(String args[]) {
        Scanner es = new Scanner(System.in);

        //introduzco el valor de las varibles
        System.out.print("Introduce valor de x: ");
        x = es.nextInt();
        System.out.print("introduce valor de b: ");
        b = es.nextInt();
        System.out.print("Introduce valor de c: ");
        c = es.nextInt();
        System.out.println();

        //instancio los hilos
        Ej_10_No h1 = new Ej_10_No(1);
        Ej_10_No h2 = new Ej_10_No(2);

        //primer y segundo hilo
        h1.start();
            //segundo hilo, q es concurrente con el primero
        n = c*x;
        System.out.println("S3: c = " + c);

        try{
            h1.join();
        }catch (InterruptedException e) {}

        //tercer y cuarto hilo
        h2.start();
            //cuarto hilo, q es concurrente con el segundo
        z = m-n;
        System.out.println("S5: z = " + z);

        try{
           h2.join();
        }catch(InterruptedException e) {}

        System.out.println("\nEl programa ha finalizado");
    }
}
