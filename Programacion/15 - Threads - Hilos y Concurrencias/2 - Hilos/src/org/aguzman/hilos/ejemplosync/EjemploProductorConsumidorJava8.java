package org.aguzman.hilos.ejemplosync;

import org.aguzman.hilos.ejemplosync.runnable.Consumidor;
import org.aguzman.hilos.ejemplosync.runnable.Panadero;

import java.util.concurrent.ThreadLocalRandom;

public class EjemploProductorConsumidorJava8 {
    public static void main(String[] args) {
        Panaderia p = new Panaderia();

        new Thread( () -> {
            for(int i = 0; i < 10; i++){
                p.hornear("Pan nº: " + i);
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread( () -> {
            for(int i = 0 ; i < 10; i++){
                p.consumir();
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
