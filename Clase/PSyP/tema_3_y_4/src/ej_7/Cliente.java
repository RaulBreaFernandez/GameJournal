package ej_7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        InetAddress direcc = null;
        Socket conexion = null;
        int opc=1;
        int num1, num2;

        try{
            try {
                direcc = InetAddress.getLocalHost();
            } catch (UnknownHostException sce) {
                System.out.println("Host no encontrado: " + sce);
            }

            conexion = new Socket(direcc, 1234);
            DataInputStream entrada = new DataInputStream(conexion.getInputStream());
            DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());

            System.out.println("\tMENÚ");
            System.out.println("0.Salir");
            System.out.println("1.Sumar");
            System.out.println("2.Restar");
            System.out.println("3.Multiplicar");
            System.out.println("4.Dividir");

            do{
                //el cliente introduce la opcion deseada y se la envio al servidor
                System.out.print("\tSeleccione opción: ");
                opc = sc.nextInt();

                salida.writeInt(opc);

                //si la opcion no es una de las posibles cierro la conexion del cliente
                if (opc!=1 && opc!=2 && opc!=3 && opc!=4) {
                    System.out.println("\nEl cliente quiso salir");
                    break;
                }

                //si es valida continuo con el programa
                System.out.print("\nEscriba primer número: ");
                num1 = sc.nextInt();
                System.out.print("Escriba segundo número: ");
                num2 = sc.nextInt();

                //le envio los numeros al servidor
                salida.writeInt(num1);
                salida.writeInt(num2);

                //recibo la respuesta del servidor y la imprimo para el cliente
                int resultado = entrada.readInt();
                System.out.println("Resultado -> " + resultado);
                System.out.println(); //esto es solo estetico

            }while (opc==1 || opc==2 || opc==3 || opc==4);

            System.out.println("Cliente cerrado");
            sc.close();
            entrada.close();
            salida.close();
            conexion.close();
        }catch (IOException e) {
            System.out.println("(cliente) Se ha producido el error: " + e);
        } finally {
            try {
                if (conexion != null)
                    conexion.close();
            } catch (IOException ioe) {
                System.err.println("Error al cerrar el socket: " + ioe);
            }
        }
    }
}
