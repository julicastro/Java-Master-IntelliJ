package org.aguzman.hilos.ejemplosync;

public class Panaderia {
    private String pan;
    private boolean disponible;

    public synchronized void hornear(String masa){
        while(disponible){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.pan = masa;
        System.out.println("Panadero hornea: " + this.pan);
        this.disponible = true;
        notify();
    }

    public synchronized String consumir(){
        while(!disponible){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Cliente consume: " + this.pan);
        this.disponible = false;
        notify();
        return pan;
    }
}
