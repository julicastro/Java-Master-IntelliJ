package org.example.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {

    /* Debemos generar un objeto de fábrica q nos permita crear
    * EntityManager. Cada objeto EntityManager es para un objeto en
    * particular o para una peticion */
    /* Nacesitamos un objeto de fabrica para crear un EntityManagerFactory
    * Este EMF es para toda la aplicacion y sirve para crear los EM q
    * se utilizan 1 por cada peticion u objeto */

    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("EjemploJPA"); // el nombre q le dimos en el XML
    }

    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
        // crea y devuelve un EM x cada hilo, request o cliente.
        // en este caso, x esta aplicacion
    }

    /* CADA VEZ Q INVOQUEMOS EL METODO, SE VA A CREAR UN EM */

}
