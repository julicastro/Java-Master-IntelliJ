package org.example.util;

import jakarta.persistence.EntityManager;
import org.example.entity.Cliente;

import javax.swing.*;

public class HibernateCrear {

    public static void main(String[] args) {

        /* debemos manejar transacciones */

        EntityManager em = JpaUtil.getEntityManager();

        try {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre: ");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido");
            String pago = JOptionPane.showInputDialog("Ingrese la forma de pago");

            em.getTransaction().begin();
            Cliente c = new Cliente();
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setFormaPago(pago);
            em.persist(c); // persist = guardar
            /* El metodo guardar debe estar dentro el Begin y el Commit*/
            em.getTransaction().commit();
            System.out.println("el id del cliente registrado es: " + c.getId());
            c = em.find(Cliente.class, c.getId());
            System.out.println(c);

        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }



    }

}
