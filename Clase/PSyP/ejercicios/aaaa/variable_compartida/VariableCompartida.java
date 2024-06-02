package variable_compartida;

public class VariableCompartida extends Thread{
    private static int contador;

    public void run() {
        for(int i=0; i<1000; i++){
            contador++; //contador hace tres operaciones: leer, sumar y guardar
        }
    }

    public static void main(String[] args) {
        for (int i=0; i<1000; i++){
            new VariableCompartida().start();
        }
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Valor del contador: " + contador); //los hilos se van entrelazando entre ellos entre operacion y operacion y no se llegan a completar algunos
    }
}
