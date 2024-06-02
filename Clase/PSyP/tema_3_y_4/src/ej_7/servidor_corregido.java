package ej_7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class servidor_corregido {
    static ServerSocket ss;
    static Socket conexion;
    static DataInputStream entrada;
    static DataOutputStream salida;
    static int opc=1, num1, num2, resultado;

    public static void main(String args[]) {
        try{
            System.out.println("LocalHost -> " + InetAddress.getLocalHost());
        }catch (UnknownHostException uhe) {
            System.err.println("Error en el Host: " + uhe);
        }

        try{
            ss = new ServerSocket(1234);

            while(true) {
                try {
                    conexion = ss.accept();
                    entrada = new DataInputStream(conexion.getInputStream());
                    salida = new DataOutputStream(conexion.getOutputStream());

                    while (opc>0 && opc<=5){
                        opc = entrada.readInt();

                        if (opc == 5) {
                            System.out.println("\tEl cliente mando Salir");
                            num1 = 1;
                            entrada.close();
                            salida.close();
                            conexion.close();
                            break;
                        }

                        num1 = entrada.readInt();
                        num2 = entrada.readInt();

                        switch (opc) {
                            case 1:
                                resultado = num1 + num2;
                                break;
                            case 2:
                                resultado = num1 - num2;
                                break;
                            case 3:
                                resultado = num1 * num2;
                                break;
                            case 4:
                                resultado = num1 / num2;
                                break;
                        }

                        System.out.println("NUM1 -> " + num1 + "\tNUM2 -> " + num2 + "\tRESULTADO -> " + resultado);
                        salida.writeInt(resultado);
                    }
                } catch (IOException ec) {
                    System.out.println("Error de conexion: " + ec);
                }
            }
        }catch (IOException e) {
            System.out.println("(servidor) Error de servidor: " + e);
        }
    }
}
