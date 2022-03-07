package org.example;

import jakarta.persistence.EntityManager;
import org.example.entity.Cliente;
import org.example.util.JpaUtil;
import java.util.List;

public class HibernateListar {

    public static void main(String[] args) {

        // HQL:

        /* obtenemos el EM para poder trabajar con JPA e Hibernate */
        EntityManager em = JpaUtil.getEntityManager();
        List <Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        clientes.forEach(System.out::println);
        em.close();





    }

}
