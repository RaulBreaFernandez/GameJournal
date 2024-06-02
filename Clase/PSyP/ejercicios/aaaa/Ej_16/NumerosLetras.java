package Ej_16;

public class NumerosLetras implements Runnable{
    private int tipo;
    public NumerosLetras(int tipo) {
        this.tipo = tipo;
    }

    public void run() {
        switch(tipo) {
            case 1:
                for (int i = 1; i <= 30; i++) {
                    System.out.print(i + " ");
                }
                break;
            case 2:
                for (char c='a'; c<='z'; c++){
                    System.out.print(c + " ");
                }
                break;
        }
    }

    public static void main(String args[]) {
        NumerosLetras h1 = new NumerosLetras(1);
        Thread num = new Thread(h1);
        NumerosLetras h2 = new NumerosLetras(2);
        Thread let = new Thread(h2);

        num.start();
        let.start();
    }
}
