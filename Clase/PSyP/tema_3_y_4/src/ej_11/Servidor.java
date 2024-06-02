package ej_11;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Servidor {
    static ServerSocket ss;
    static Socket conexion;
    static DataInputStream entrada;
    static DataOutputStream salida;
    static String opcion="hola", respuesta;
    static ArrayList<String> libros = new ArrayList<>();
    static ArrayList<String> frases = new ArrayList<>();

    public static void main(String args[]) {
        try{
            System.out.println("LocalHost -> " + InetAddress.getLocalHost());
        }catch (UnknownHostException uhe) {
            System.err.println("Error en el LocalHost: " + uhe);
        }

        //lleno el array de libros
        libros.add("Divina Comedia - Dante Alighieri");
        libros.add("Don Quijote de la Mancha - Miguel de Cervantes");
        libros.add("Cien años de soledad - Gabriel García Márquez");
        libros.add("Dick - Herman Melville");
        libros.add("Ana Karenina - Lev Tolstói");
        libros.add("Eneida - Virgilio");
        libros.add("Otelo - William Shakespeare");
        libros.add("El viejo y el mar - Ernest Hemingway");
        libros.add("Orgullo y prejuicio - Jane Austen");

        //lleno el array de frases
        frases.add("La tecnología se alimenta a si misma. La tecnología hace posible más tecnología.-Alvin Toffler.");
        frases.add("La tecnología es sólo una herramienta. En términos de llevar a los niños a trabajar juntos y motivarlos, el profesor es el más importante.-Bill Gates.");
        frases.add("La máquina tecnológicamente más eficiente que el hombre ha inventado es el libro.-Northrop Frye.");
        frases.add("Ya no hacen más los bugs como bunny - Olav Mjelde");
        frases.add("Un lenguaje de programación es de bajo nivel cuando requiere que prestes atencion a lo irrelevante.-Alan J. Perlis.");
        frases.add("Hablar es barato. Enséñame el código.-Linus Torvalds");
        frases.add("No me importa si funciona en su máquina! No me envían su máquina!.-Vidiu Platon");
        frases.add("Siempre codifica como si la persona que finalmente mantendrá tu código fuera un psicópata violento que sabe dónde vives.-Martin Golding");

        //realizo la accion del servidor
        try{
            ss = new ServerSocket(1234);

            while (true) {
                try {
                    conexion = ss.accept();
                    entrada = new DataInputStream(conexion.getInputStream());
                    salida = new DataOutputStream(conexion.getOutputStream());

                    System.out.println("\n\tUn cliente ha abierto una conexion");

                    while (!opcion.equalsIgnoreCase("adios")) {
                        opcion = entrada.readUTF();

                        if (opcion.equalsIgnoreCase("adios")) {
                            System.out.println("\tEl cliente ha querido salir");
                            opcion="hola"; //esto es para q si otro cliente se conecta, opcion no empiece en adios
                            entrada.close();
                            salida.close();
                            conexion.close();
                            break;
                        }

                        switch (opcion) {
                            case "libro":
                                Collections.shuffle(libros);
                                respuesta = libros.get(0);
                                break;
                            case "frase":
                                Collections.shuffle(frases);
                                respuesta = frases.get(0);
                                break;
                            default:
                                respuesta = "Inserte una opcion válida";
                                break;
                        }

                        System.out.println("El servidor ha mandado " + opcion);
                        salida.writeUTF(respuesta);
                    }

                } catch (IOException e2) {
                    System.out.println("Error de conexion: " + e2);
                }
            }
        }catch (IOException e){
            System.out.println("(servidor) Error: " + e);
        }
    }
}
