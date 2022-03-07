package com.crud.models.dao;

import com.crud.models.entity.Cliente;

import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

    /* no necesita anotacion xq x debajo ya es un componente spring al 
    heredar de crud repository */


}
