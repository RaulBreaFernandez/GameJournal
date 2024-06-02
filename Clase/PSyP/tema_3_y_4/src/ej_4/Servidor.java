package tema_3_y_4.src.ej_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Servidor {
    static ServerSocket ss;
    static Socket conexion;
    static DataInputStream entrada;
    static DataOutputStream salida;
    static int numero=1;

    public static void main(String args[]) {
        try{
            System.out.println("LocalHost -> " + InetAddress.getLocalHost());
        }catch (UnknownHostException uhe) {
            System.err.println("Hubo un problema con el Host: " + uhe);
        }

        try{
            ss = new ServerSocket(1234);

            while (true) {
                try{
                    conexion = ss.accept();
                    entrada = new DataInputStream(conexion.getInputStream());
                    salida = new DataOutputStream(conexion.getOutputStream());

                    while(numero!=0){
                        numero = entrada.readInt();

                        if(numero==0) {
                            System.out.println("El cliente mandó 0");
                            numero = 1; //cnd el cliente manda 0, esto vuelve a inicializar numeo a 1 para no tener q volver a correr el servidor si realizo otra conexion
                            entrada.close();
                            salida.close();
                            conexion.close();
                            break;
                        }

                        int respuesta = 1;
                        for (int i = 1; i <= numero; i++) {
                            respuesta *= i;
                        }

                        System.out.println("Cliente: " + numero + "\tRespuesta: " + respuesta);
                        salida.writeInt(respuesta);
                    }
                }catch (IOException ec) {
                    System.out.println("Error de conexión: " + ec);
                }
            }
        }catch (IOException e) {
            System.out.println("(servidor) Error -> " + e);
        }



    }
}


