package Ej_13;

public class MainThreat extends Thread {
    private Cliente cliente;
    private Cajera cajera;
    public MainThreat(Cliente cliente, Cajera cajera) {
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

        MainThreat h1 = new MainThreat(cliente1, cajera1);
        MainThreat h2 = new MainThreat(cliente2, cajera2);

        h2.start();
        h1.start();

    }
}
