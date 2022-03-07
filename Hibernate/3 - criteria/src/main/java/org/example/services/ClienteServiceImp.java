package org.example.services;

import jakarta.persistence.EntityManager;
import org.example.entity.Cliente;
import org.example.repositories.ClienteRepository;
import org.example.repositories.CrudRepository;
import org.example.util.JpaUtil;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImp implements ClienteService{

    private EntityManager em;
    private CrudRepository<Cliente> repository;

    public ClienteServiceImp(EntityManager em) {
        this.em = em;
        // con el em creamos instancia del CrudRepository
        this.repository = new ClienteRepository(em);
    }

    /* EN ESTE CASO EL EM DEBE CERRARSE PERO LO HACEMOS FUERA DEL SERVICE */

    @Override
    public List<Cliente> listar() {
        return repository.listar();
    }

    @Override
    public Optional<Cliente> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
        // of = del tipo tal
        // nulable = puede ser nulo
    }

    @Override
    public void guardar(Cliente c) {
        try {
            em.getTransaction().begin();
            repository.guardar(c);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            em.getTransaction().begin();
            repository.eliminar(id);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
