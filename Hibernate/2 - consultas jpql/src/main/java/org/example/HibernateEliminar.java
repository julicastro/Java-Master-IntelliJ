
package org.example;

import jakarta.persistence.EntityManager;
import jdk.incubator.foreign.CLinker;
import org.example.entity.Cliente;
import org.example.util.JpaUtil;

import java.util.Scanner;

public class HibernateEliminar {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese id del cliente a eliminar: ");
        Long id = s.nextLong();
        EntityManager em = JpaUtil.getEntityManager();

        try{
            Cliente c = em.find(Cliente.class, id);
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }




    }
}