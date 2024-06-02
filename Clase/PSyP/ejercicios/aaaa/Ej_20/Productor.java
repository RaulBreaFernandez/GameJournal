package Ej_20;

public class Productor extends Thread{
    private Monitor monitor;
    private int car_prod;
    private int tiempo;

    public Productor(Monitor monitor, int car_prod, int tiempo) {
        this.monitor = monitor;
        this.car_prod = car_prod;
        this.tiempo = tiempo;
    }

    public void run() {
        try{
            char c = 0;
            for(int i=0; i<car_prod; i++) {
                c = (char) ('A'+i);
                monitor.poner(c);
                sleep((int) (Math.random() * tiempo));
            }
        }catch (Exception e) {}
    }
}
