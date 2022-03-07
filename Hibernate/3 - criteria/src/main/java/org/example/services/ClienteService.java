package org.example.services;

import org.example.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> listar();
    Optional <Cliente> porId(Long id);
    void guardar(Cliente c);
    void eliminar(Long id);


}
