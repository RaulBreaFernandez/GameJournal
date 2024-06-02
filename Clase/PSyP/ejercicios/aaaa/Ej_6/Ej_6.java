package Ej_6;

import java.util.Scanner;

public class Ej_6 extends Thread{
    private int num;
    private static int a,b,c,d,x,y,z;
    public Ej_6(int num) {
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
        Ej_6 h1 = new Ej_6(1);
        Ej_6 h2 = new Ej_6(2);

        h1.start();
        h2.start();

        try{
            h1.join();
            h2.join();
        }catch (InterruptedException e) {}

        //tercer hilo
        d = a+c;
        System.out.println("S4: d = " + d);

        System.out.println("\nEl programa ha finalizado");
    }
}
