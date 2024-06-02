package Ej_5;

import java.util.Scanner;

public class Ej_5 extends Thread{
    private int num; //define q hilo estas lanzando
    private static int a;
    private static int b;
    private static int c;
    private static int d;
    private static int x;
    private static int y;
    private static int z;

    public Ej_5(int _num) {
        this.num = _num;
    }


    public void run() {
        switch (num) {
            case 1:
                x = (a+b) * (a-b);
                System.out.println("S1: x = " + x);
                break;
            case 2:
                y = (c-d) * (c+d);
                System.out.println("S2: y = " + y);
                break;
        }
    }


    public static void main(String args[]){
        Scanner es = new Scanner(System.in);

        //defino las variables
        System.out.print("Introduzca valor de a: ");
        a = es.nextInt();
        System.out.print("Introduzca valor de b: ");
        b = es.nextInt();
        System.out.print("Introduzca valor de c: ");
        c = es.nextInt();
        System.out.print("Introduzca valor de d: ");
        d = es.nextInt();

        //inicializo los hilos
        Ej_5 h1 = new Ej_5(1);
        Ej_5 h2 = new Ej_5(2);

        //hacer q empiecen los hilos
        h1.start();
        h2.start();

        //hacer q el main para hasta q se haya acabado la ejecucion de los hilos
        try{
            h1.join();
            h2.join();
        }catch(InterruptedException e) {}

        //imprimir el resultado de z
        z = x+y;
        System.out.println("S3: z = " + z);
    }
}