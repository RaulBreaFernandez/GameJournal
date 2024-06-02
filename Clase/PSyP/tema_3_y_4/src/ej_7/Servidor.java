//Realizar un proyecto cliente-servidor en el que el cliente elija en un menú la operación que quiere
//realizar de las cuatro elementales. Luego le enviará al servidor dos números y el servidor le
//devolverá el resultado. La conexión seguirá en marcha mientras el cliente no elija la quinta opción
//del menú que será Salir.

package ej_7;

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
    static int opc, num1, num2, resultado;


    public static void main(String args[]) {
        try {
            System.out.println("LocalHost = " + InetAddress.getLocalHost().toString());
        } catch (UnknownHostException sce) {
            System.err.println("No puedo saber la direccion IP local : " + sce);
        }


        try{
            ss = new ServerSocket(1234);

            while (true) {
                try{
                    conexion = ss.accept();
                    entrada = new DataInputStream(conexion.getInputStream());
                    salida = new DataOutputStream(conexion.getOutputStream());

                    opc = entrada.readInt();

                    while(opc<5 && opc>0){
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
                    }
//poner el bucle while aqui
                    //leo lo q envia el cliente
                    opc = entrada.readInt();
                    num1 = entrada.readInt();
                    num2 = entrada.readInt();

                    //si la opcion no es una de las válidas cierro la conexion con el cliente
                    if (opc!=1 && opc!=2 && opc!=3 && opc!=4){
                        System.out.println("prueba a ver si entrar");
                        entrada.close();
                        salida.close();
                        conexion.close();
                    }

                    //segun la opcion q mande, hago lo q toca
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

                    //"guardo" la informacion de la conexion con el cliente y le envio el resultado
                    System.out.println("num1 -> " + num1 + "// num2 -> " + num2 + "// opcion -> " + opc +"// resultado -> " + resultado);
                    salida.writeInt(resultado);

                }catch (IOException err) {
                    System.out.println("Error: " + err);
                }
            }
        }catch (IOException e){
            System.out.println("Error: " + e);
        }
    }
}
