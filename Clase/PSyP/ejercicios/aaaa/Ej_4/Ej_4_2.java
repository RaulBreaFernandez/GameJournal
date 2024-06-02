package Ej_4;

import java.util.Scanner;
public class Ej_4_2 implements Runnable{
    private int num;
    private static int a,b,c,d,u;
    private static double x,v,w;

    public Ej_4_2(int num) {
        this.num = num;
    }

    public void run() {
        switch(num) {
            case 1:
                v = u + c;
                System.out.println("P2: v = " + v);
                break;
            case 2:
                w = u - d;
                System.out.println("P3: w = " + w);
        }
    }

    public static void main(String args[]){
        Scanner es = new Scanner(System.in);

        //introduzco las variables
        System.out.print("Introduce a: ");
        a = es.nextInt();
        System.out.print("Introduce b: ");
        b = es.nextInt();
        System.out.print("Introduce c: ");
        c = es.nextInt();
        System.out.print("Introduce d: ");
        d = es.nextInt();
        System.out.println();

        //primer hilo
        u = a*b;
        System.out.println("P1: u = " + u);

        //segundo y tercer hilo
        Ej_4_2 h1 = new Ej_4_2(1);
        Thread t1 = new Thread(h1);
        Ej_4_2 h2 = new Ej_4_2(2);
        Thread t2 = new Thread(h2);
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e) {};

        //cuarto hilo
        x = v/w;
        System.out.println("P4: x = " + x);

        System.out.println("\nEl programa ha finalizado");
    }
}
