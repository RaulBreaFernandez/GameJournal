package ejercicios.tema3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorEj7Tema3 {
    static DataInputStream dis;
    static DataOutputStream dos;
    static ServerSocket ss;
    static Socket socket;
    public static void main(String[]args) throws IOException {
        int num1, num2, op=1;

        try {
            ss = new ServerSocket(11);
            while(true) {
                try {
                    System.out.println("hola");
                    socket = ss.accept();
                    dis = new DataInputStream(socket.getInputStream());
                    dos = new DataOutputStream(socket.getOutputStream());
                    System.out.println("hola");
                    op = dis.readInt();
                    System.out.println("aaaaa");
                    while(op > 0 && op < 5) {
                        num1 = dis.readInt();
                        num2 = dis.readInt();
                        switch (op) {
                            case 1:
                                dos.writeInt(num1+num2);
                                break;
                            case 2:
                                dos.writeInt(num1-num2);
                                break;
                            case 3:
                                dos.writeInt(num1 * num2);
                                break;
                            case 4:
                                dos.writeFloat((float) num1 / num2);
                                break;
                        }
                        op = dis.readInt();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            dis.close();
            dos.close();
        }
    }
}
