package Ej_20;

public class Monitor {
    private int tope = 0;
    private char[] buff = null;
    private boolean lleno = false, vacio = true;

    public Monitor(int capacidad){
        buff = new char[capacidad];
    }

    public synchronized void poner(char c) {
        while (lleno) {
            try{
                wait();
            }catch (InterruptedException e) {}
        }
        buff[tope++] = c;
        System.out.println("Produje: " + c);
        vacio = false;
        lleno = tope > buff.length; //lleno = true si tope>longitud del buff
        notifyAll();
    }

    public synchronized void sacar() throws InterruptedException {
        while (vacio){
            try{
                wait();
            }catch (InterruptedException e){}
        }

        char c = buff[--tope];
        System.out.println("Consumi: \t" + c);
        lleno = false;
        vacio = tope <= 0;
        notifyAll();
    }

}
