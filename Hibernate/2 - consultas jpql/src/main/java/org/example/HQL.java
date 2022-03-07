package org.example;

import jakarta.persistence.EntityManager;
import org.example.dominio.ClienteDto;
import org.example.entity.Cliente;
import org.example.util.JpaUtil;

import java.util.Arrays;
import java.util.List;

public class HQL {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        /* CONSULTAS HQL */

        System.out.println("Consultar todos: ");
        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("Consultar filtrando por ID: ");
        Cliente cliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class)
                .setParameter("id", 1L) // mismo nombre q el id
                .getSingleResult();
        System.out.println(cliente);

        System.out.println("Consultar nombre por ID: ");
        String nombreCliente = em.createQuery("select c.nombre from Cliente c where c.id=:id", String.class)
                .setParameter("id", 2L)
                .getSingleResult();

        // CADA VEZ Q LA CONSULTA TIENE UN "WHERE" DEBEMOS PASAR UN SetParameter

        System.out.println("Consultar por campos personalizados: ");
        Object[] campos = em.createQuery
                        ("select c.id, c.nombre, c.apellido from Cliente c where c.id=:id", Object[].class)
                .setParameter("id", 1L)
                .getSingleResult();
        Long id = (Long) campos[0];
        String nombre = (String) campos[1];
        String apellido = (String) campos[2];
        System.out.println(id + " " + nombre + " " + apellido);

        System.out.println("Consultar por campos personalizados con List: ");
        List<Object[]> registros = em.createQuery
                        ("select c.id, c.nombre, c.apellido from Cliente c", Object[].class)
                .getResultList();
        // for (Object[] reg : registros){
        registros.forEach(reg -> {
            Long idCli = (Long) reg[0];
            String nombreCli = (String) reg[1];
            String apellidoCli = (String) reg[2];
            System.out.println(id + " " + nombre + " " + apellido);
        });
        // }

        System.out.println("Consulta por Cliente y forma de pago (Objeto + Atrib del Obj)");
        List<Object[]> registros2 = em.createQuery("select c, c.formaPago from Cliente c", Object[].class)
                .getResultList();
        registros2.forEach(reg -> {
            Cliente c = (Cliente) reg[0];
            String formaPago = (String) reg[1];
        });

        System.out.println("Consulta que pobla y devuelve objeto entity de una clase personalizada");
        List<Cliente> clientes1 =
                em.createQuery("select new Cliente(c.nombre, c.apellido) from Cliente c", Cliente.class)
                        // creamos un objeto q se devuelve en la sentencia
                        // creamos lista nueva copiando solo nombre y apellido de la otra lista
                        .getResultList();
        clientes1.forEach(System.out::println);

        System.out.println("Consulta que pobla y devuelve objeto otro (dto) de una clase personalizada");
        List<ClienteDto> clientesDto =
                em.createQuery("select new org.example.dominio.ClienteDto(c.nombre, c.apellido) from Cliente c", ClienteDto.class)
                        // necesitamos poner el package xq no es una clase entity, si no una clase DTO
                        .getResultList();
        clientesDto.forEach(System.out::println);

        System.out.println("Lista con todos los nombres");
        List<String> nombres = em.createQuery("select c.nombre from Cliente c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("Consultar nombres q no se repitan");
        nombres = em.createQuery("select distinct(c.nombre) from Cliente c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("consultar formas de pago Unicas");
        List<String> formasPago = em.createQuery("select distinct(c.formaPago) from Cliente c", String.class)
                .getResultList();
        formasPago.forEach(System.out::println);

        System.out.println("consultar numero de formas de pago únicas");
        /*Long totalFormaPago = em.createQuery("select count(distinct(c.formaPago)) from Cliente c", Long.class)
                .getSingleResult();
        /*formasPago.forEach(System.out::println);*/

        System.out.println("Consulta con nombre y apellido concatenados");
        nombres = em.createQuery("select concat(c.nombre, ' ', c.apellido) as nombreCompleto from Cliente c", String.class)
                .getResultList();
        nombres.forEach(System.out::println);

        // otra forma de lo mismo
        /*nombres = em.createQuery("select c.nombre || ' ' || c.apellido as nombreCompleto from Cliente c", String.class)
                .getResultList();
        */

        System.out.println("Consulta con nombre y apellido concatenados en MAYUSCULA");
        nombres = em.createQuery("select upper( concat(c.nombre, ' ', c.apellido)) as nombreCompleto from Cliente c", String.class)
                .getResultList();
        // con Lower() lo ponemos en minuscula. Siempre q sea de tipo String para ambos

        // BUSCAR POR COINCIDENCIA: (LIKE)
        System.out.println("buscar por coincidencia x nombre");
        String param = "LU"; // buena practina en mayuscula
        clientes = em.createQuery("select c from Cliente c where c.nombre like upper( :parametro)", Cliente.class)
                .setParameter("parametro", "%" + param + "%") // % para q busque a la izq y der
                .getResultList();// si busco "lu" trae a "lucy" y "luna"

        // RANGOS (Fechas, caracteres, id, etc)
        System.out.println("consultas por rangos con Beetween");
        clientes = em.createQuery("select c from Cliente c where c.id between 2 and 5", Cliente.class)
                .getResultList();
        clientes = em.createQuery("select c from Cliente c where c.nombre between 'J' and 'P'", Cliente.class)
                .getResultList(); // no incluye la P

        // ORDER BY
        System.out.println("consultas con ordenamiento");
        clientes = em.createQuery("select c from Cliente c order by c.nombre asc", Cliente.class)
                .getResultList();
        clientes = em.createQuery("select c from Cliente c order by c.nombre desc", Cliente.class)
                .getResultList();
        clientes = em.createQuery("select c from Cliente c order by c.nombre, c.apellido asc", Cliente.class)
                .getResultList(); // ordena x nombre, y si se repite, ordena x apellido

        // FUNCION DE AGREGACION
        System.out.println("contar total de registros de la tabla");
        Long total = em.createQuery("select count(c) as total from Cliente c", Long.class)
                .getSingleResult();

        // MINIMO
        System.out.println("consulta con valor minimo/maximo del id");
        Long min = em.createQuery("select min(c.id) as minimo from Cliente c", Long.class)
                .getSingleResult();
        Long max = em.createQuery("select max(c.id) as minimo from Cliente c", Long.class)
                .getSingleResult();

        // LENGTH
        System.out.println("consulta con nombre y su largo");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c", Object[].class)
                .getResultList();
        registros.forEach(reg -> {
            String n = (String) reg[0];
            Integer largo = (Integer) reg[1];
            System.out.println(n + largo);
        });

        // EL NOMBRE MAS CORTO
        System.out.println("consultar el nombre mas corto");
        Integer minLargoNombre = em.createQuery("select min(length(c.nommbre)) from Cliente c", Integer.class)
                .getSingleResult();

        System.out.println("consultar resumen funciones agregaciones count min max avg(promedio) sum");
        Object[] estadisticas = em.createQuery("select min(c.id), max(c.id), sum(c.id), count(c.id), avg(length(c.nombre)) from Cliente c ", Object[].class)
                .getSingleResult();
        Long minimoLong = (Long) estadisticas[0];

        // SUBCONSULTAS
        System.out.println("consulta con nombre mas corto y su largo");
        registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c where length(c.nombre) = (select min(length(c.nombre)) from Cliente c), Object[].class")
                .getResultList();
        registros.forEach(reg -> {
            String nane = (String) reg[0];
        });

        System.out.println("obtener ultimo registro");
        Cliente ultimoCliente = em.createQuery("select c from Cliente c where c.id = (select max(c.id) from Cliente c)", Cliente.class)
                .getSingleResult();

        System.out.println("consulta where in");
        // clientes = em.createQuery("select c from Cliente c where c.id in (1,2,10) ", Cliente.class)
        clientes = em.createQuery("select c from Cliente c where c.id in :ids ", Cliente.class)
                .setParameter("ids", Arrays.asList(1L,2L,10L, 40L))
                .getResultList();

        em.close();
    }
}
