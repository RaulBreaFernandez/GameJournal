package tema_3_y_4.src.ej_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        InetAddress direcc = null;
        Socket conexion;
        int numero=1;

        try{
            try{
                direcc = InetAddress.getLocalHost();
            }catch (UnknownHostException uhe) {
                System.out.println("Error host: " + uhe);
            }

            conexion = new Socket(direcc, 1234);
            DataInputStream entrada = new DataInputStream(conexion.getInputStream());
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

            while (numero != 0) {
                System.out.print("Número del que quiera el factorial: ");
                numero = sc.nextInt();

                salida.writeInt(numero);

                if (numero == 0) {
                    System.out.println("\nAdios..... :)");
                    break;
                }

                System.out.println("\tRespuesta -> " + entrada.readInt());
                System.out.println(); //esto es solo estetico
            }
        }catch (IOException e) {
            System.out.println("(cliente) Error -> " + e);
        }
    }
}
