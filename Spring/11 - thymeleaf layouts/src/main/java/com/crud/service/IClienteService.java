package com.crud.service;

import java.util.List;
import com.crud.models.entity.Cliente;



public interface IClienteService {
    
    public List <Cliente> findAll(); // read
    public void save (Cliente cliente); // crear
    public Cliente findOne(Long id); // update
    public void delete(Long id); // delte

}
