package Ej_4;//1, 2-3, 4
import java.util.Scanner;
public class Ej_4 extends Thread{
    private int num;
    private static int a,b,c,d,u;
    private static double x,v,w;

    public Ej_4 (int num) {
        this.num = num;
    }

    public void run(){
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

    public static void main (String args[]) {
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
        Ej_4 h1 = new Ej_4(1);
        Ej_4 h2 = new Ej_4(2);
        h1.start();
        h2.start();
        try{
            h1.join();
            h2.join();
        }catch (InterruptedException e) {}

        //cuarto hilo
        x = v/w;
        System.out.println("P4: x = " + x);

        System.out.println("\nEl programa ha finalizado");
    }
}
