package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.entity.Cliente;
import org.example.util.JpaUtil;

import java.util.Scanner;

public class HibernatePorId {

    public static void main(String[] args) {


        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese el ID: ");
        Long id = s.nextLong();
        EntityManager em = JpaUtil.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        // getSingleResult devuelve un objet. debemos castear a Cliente
        /* con find el objeto se guarda en memoria. o sea en la sesion de nuestro
         contrexto. si volvemos a realizar esta consulta no va a la bd si no q va
         directamente a la memoria. es un cache de hibernate.s
         o sea solo hace 1 consulta para 2 llamados del mismo ID
         */
        System.out.println(cliente);
        em.close();



    }
}
