package Ej_6;

import java.util.Scanner;
public class Ej_6_2 implements Runnable{
    private int num;
    private static int a,b,c,d,x,y,z;
    public Ej_6_2(int num) {
        this.num = num;
    }

    public void run(){
        switch(num){
            case 1:
                a = 2*x;
                System.out.println("S1: a = " + a);
                break;
            case 2:
                b = y-z;
                System.out.println("S2: b = " + b);
                c = b/3;
                System.out.println("S3: c = " + c);
        }
    }

    public static void main(String args[]) {
        Scanner es = new Scanner(System.in);

        //introduzco las variables
        System.out.print("Introduce x: ");
        x = es.nextInt();
        System.out.print("Introduce y: ");
        y = es.nextInt();
        System.out.print("Introduce z: ");
        z = es.nextInt();
        System.out.println();

        //primer y segundo hilo
        Ej_6_2 h1 = new Ej_6_2(1);
        Thread t1 = new Thread(h1);
        Ej_6_2 h2 = new Ej_6_2(2);
        Thread t2 = new Thread(h2);

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e) {}

        //tercer hilo
        d = a+c;
        System.out.println("S4: d = " + d);

        System.out.println("\nEl programa ha finalizado");
    }
}
