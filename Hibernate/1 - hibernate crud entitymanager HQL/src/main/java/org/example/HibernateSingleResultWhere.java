package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.entity.Cliente;
import org.example.util.JpaUtil;

import java.util.Scanner;

public class HibernateSingleResultWhere {

    public static void main(String[] args) {


        Scanner s = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.formaPago=?1", Cliente.class);
        // "?1" => 1 porque usamos 1 solo parametro
        // Query query ya q el em aca devuelve una instancia del query
        System.out.println("Ingrese una forma de pago: ");
        String pago = s.next();
        query.setParameter(1, pago);
        query.setMaxResults(1); // devuelve como maximo 1 solo registro
        // Nos devuelve el primero q encuentre
        // conviene mas usar GetResultList
        Cliente c = (Cliente) query.getSingleResult();
        // getSingleResult devuelve un objet. debemos castear a Cliente
        System.out.println(c);
        em.close();



    }
}
