package Ej_10;

import java.util.Scanner;
public class Ej_10 extends Thread{
    private int num;
    private static int a,m,n,y,z,x,b,c;

    public Ej_10(int num) {
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
                n = c*x;
                System.out.println("S3: c = " + c);
                break;
            case 3:
                y = m+n;
                System.out.println("S4: y = " + y);
                break;
            case 4:
                z = m-n;
                System.out.println("S5: z = " + z);
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
        Ej_10 h1 = new Ej_10(1);
        Ej_10 h2 = new Ej_10(2);
        Ej_10 h3 = new Ej_10(3);
        Ej_10 h4 = new Ej_10(4);

        //primer y segundo hilo
        h1.start();
        h2.start();

        try{
            h1.join();
            h2.join();
        }catch (InterruptedException e) {}

        //tercer y cuarto hilo
        h3.start();
        h4.start();

        try{
            h3.join();
            h4.join();
        }catch(InterruptedException e) {}

        System.out.println("\nEl programa ha finalizado");
    }
}
