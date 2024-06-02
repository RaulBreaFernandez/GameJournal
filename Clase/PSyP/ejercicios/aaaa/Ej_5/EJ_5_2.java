package Ej_5;

import java.util.Scanner;

public class EJ_5_2 implements Runnable {

    private int num;
    private static int a;
    private static int b;
    private static int c;
    private static int d;
    private static int x;
    private static int y;

    public EJ_5_2(int num){
        this.num = num;
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

        System.out.print("Introduzca valor de a: ");
        a = es.nextInt();
        System.out.print("Introduzca valor de b: ");
        b = es.nextInt();
        System.out.print("Introduzca valor de c: ");
        c = es.nextInt();
        System.out.print("Introduzca valor de d: ");
        d = es.nextInt();

        //defino los hilos
        EJ_5_2 h1 = new EJ_5_2(1);
        Thread t1 = new Thread(h1);
        EJ_5_2 h2 = new EJ_5_2(2);
        Thread t2 = new Thread(h2);

        //hacer q empiecen los hilos
        t1.start();
        t2.start();

        //hacer q el main pare hasta q se haya acabado la ejecucion de los hilos
            //espera pasiva
        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e) {}
            //espera activa
 //       while (t1.isAlive() || t2.isAlive()) {
 //       }

        //imprimir el resultado de z
        System.out.println("S3: z = " + (x+y));
    }


}
