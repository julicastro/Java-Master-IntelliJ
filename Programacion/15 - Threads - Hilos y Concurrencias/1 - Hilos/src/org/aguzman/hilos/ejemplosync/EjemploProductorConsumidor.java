package org.aguzman.hilos.ejemplosync;

import org.aguzman.hilos.ejemplosync.runnable.Consumidor;
import org.aguzman.hilos.ejemplosync.runnable.Panadero;

public class EjemploProductorConsumidor {
    public static void main(String[] args) {
        Panaderia p = new Panaderia();
        new Thread(new Panadero(p)).start();
        new Thread(new Consumidor(p)).start();
    }
}
