package Ej_13;

public class MainRunnable implements Runnable{
    private Cliente cliente;
    private Cajera cajera;
    public MainRunnable(Cliente cliente, Cajera cajera) {
        this.cliente = cliente;
        this.cajera = cajera;
    }

    public void run() {
        cajera.procesarCompra(cliente);
    }

    public static void main(String args[]) {
        Cliente cliente1 = new Cliente("Cliente 1", new int[] {2,2,1,5,2,3});
        Cliente cliente2 = new Cliente("Cliente 2", new int[] {1,3,5,1,1});

        Cajera cajera1 = new Cajera("Cajera 1");
        Cajera cajera2 = new Cajera("Cajera 2");

        MainRunnable h1 = new MainRunnable(cliente1, cajera1);
        Thread t1 = new Thread(h1);
        MainRunnable h2 = new MainRunnable(cliente2, cajera2);
        Thread t2 = new Thread(h2);

        t2.start();
        t1.start();
    }
}
