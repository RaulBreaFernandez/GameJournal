package ejercicios.tema3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEj7Tema3 {
    static DataOutputStream dos;
    static DataInputStream dis;
    static Socket socket;

    public static void main(String[] args) {
        Scanner es = new Scanner(System.in);
        int op=1, num1, num2, resul;

        try{
            socket = new Socket("localhost", 11);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            do{
                System.out.println("Elige una opción");
                System.out.println("1 - Suma");
                System.out.println("2 - Resta");
                System.out.println("3 - Multiplicación");
                System.out.println("4 - División");
                System.out.println("5 - Salir");
                op = es.nextInt();
                dos.writeInt(op);

                if(op == 5) {
                    System.exit(0);
                }

                System.out.println("Introduce un número");
                num1 = es.nextInt();
                dos.writeInt(num1);
                System.out.println("Introduce un segundo número");
                num2 = es.nextInt();
                dos.writeInt(num2);
                resul=dis.readInt();
                System.out.println(resul);
            }while(op > 0 && op < 5);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}


