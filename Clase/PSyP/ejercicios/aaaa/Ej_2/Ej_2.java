package Ej_2;

import java.util.Scanner;
public class Ej_2 extends Thread{
    private int num;
    private static int x,a,b,m1,m2,z,c,y,cuad;

    public Ej_2(int num) {
        this.num = num;
    }

    public void run(){
        switch (num) {
            case 1:
                cuad = x*x;
                System.out.println("S1: cuad = " + cuad);
                m1 = a*cuad;
                System.out.println("S2: m1 = " + m1);
                break;
            case 2:
                m2 = b*x;
                System.out.println("S3: m2 = " + m2);
        }

    }

    public static void main (String args[]) {
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
        System.out.println("\n");

        //instancio los hilos
        Ej_2 h1 = new Ej_2(1);
        Ej_2 h2 = new Ej_2(2);

        //los dos primeros hilos
        h1.start();
        h2.start();
            //paralizo la ejecucion hasta q han finalizado h1 y h2
        try{
            h1.join();
            h2.join();
        }catch (InterruptedException e) {}

        //el ultimo hilo (no es necesario instanciar un tercer hilo porq ya es el final)
        z = m1+m2;
        System.out.println("S4: z = " + z);
        y = z+c;
        System.out.println("S5: y = " + y);

        //mando un mensaje final
        System.out.println("\nEl programa ha finalizado");

    }
}
