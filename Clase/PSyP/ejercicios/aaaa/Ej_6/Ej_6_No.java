package Ej_6;//tengo q hacerlo enviando dos hilos, uno q ya tengo y otro con S1
import java.util.Scanner;
public class Ej_6_No extends Thread{
   private static int a,b,c,d,x,y,z;

   public void run(){
       b = y-z;
       System.out.println("S2: b = " + b);
       c = b/3;
       System.out.println("S3: c = " + c);
   }

   public static void main(String args[]){
       Scanner es = new Scanner(System.in);

       //introduzco las variables
       System.out.print("Introduce x: ");
       x = es.nextInt();
       System.out.print("Introduce y: ");
       y = es.nextInt();
       System.out.print("Introduce z: ");
       z = es.nextInt();
       System.out.println();

       //segundo hilo
       Ej_6_No h1 = new Ej_6_No();
       h1.start();
        //primer hilo, q es concurrente con h1
       a = 2*x;
       System.out.println("S1: a = " + a);

       //paralizo la ejecucion hasta q acaba h1
       try{
           h1.join();
       }catch(InterruptedException e) {};

       //tercer hilo
       d = a+c;
       System.out.println("S4: d = " + d);

       System.out.println("\nEl programa ha finalizado");
   }
}
