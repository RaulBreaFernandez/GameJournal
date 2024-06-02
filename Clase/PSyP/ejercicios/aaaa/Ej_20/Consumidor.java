package Ej_20;

public class Consumidor extends Thread{
    private Monitor monitor;
    private int car_prod;
    private int tiempo;

    public Consumidor(Monitor monitor, int car_prod, int tiempo) {
        this.monitor = monitor;
        this.car_prod = car_prod;
        this.tiempo = tiempo;
    }

    @Override
    public void run() {
        try{
            char c =0;
            for(int i =0; i<car_prod; i++){
                c  = (char) ('A'+i);
                monitor.sacar();
                sleep((int) (Math.random() * tiempo));
            }
        }catch (Exception e) {}
    }
}
