package org.example.util;

import jakarta.persistence.EntityManager;
import org.example.entity.Cliente;
import org.example.services.ClienteService;
import org.example.services.ClienteServiceImp;

import java.util.List;
import java.util.Optional;

public class HibernateCrudService {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        ClienteService service = new ClienteServiceImp(em);

        System.out.println("========= Listar =========");
        List <Cliente> clientes = service.listar();
        clientes.forEach(System.out::println);
        System.out.println("========== obtener por id ===========");
        Optional<Cliente> optionalCliente = service.porId(1L);
        /* if(optionalCliente.isPresent()){
            System.out.println(optionalCliente.get());
        } */
        optionalCliente.ifPresent(System.out::println);
                System.out.println("========== insertar nuevo cliente ===========");
        Cliente cliente = new Cliente();
        cliente.setNombre("Julian");
        cliente.setApellido("Castro");
        cliente.setFormaPago("Debito");
        service.guardar(cliente);
        System.out.println("cliente guardado con exito");
        service.listar().forEach(System.out::println); // para ver los cambios
        System.out.println("========== editar cliente ===========");
        Long id = cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c -> {
            c.setFormaPago("Credito");
            service.guardar(c);
            System.out.println("cliente editado con exito");
            service.listar().forEach(System.out::println); // para ver los cambios
        });
        System.out.println("========== eliminar cliente ===========");
        id = cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c -> {
            service.eliminar(c.getId());
        });

        if(optionalCliente.isPresent()){
            service.eliminar(id);
        }
        service.listar().forEach(System.out::println); // para ver los cambios
        System.out.println("cliente eliminado con exito");

        em.close();
    }
}
