package com.crud.service;

import java.util.List;

import com.crud.models.dao.IClienteDao;
import com.crud.models.entity.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service/* unico punto de acceso a todas las clases dao */
public class ClienteServiceImp implements IClienteService {

    /* x cada metodo en la clase dao vamos a tener metodos aca */

    @Autowired
    private IClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true) // solo lectura xq solo es una consulta
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional // sin readOnly xq inserta nuevo registro
    public void save(Cliente cliente) {
        clienteDao.save(cliente);
        
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return clienteDao.findById(id).orElse(null); // si lo encuentra lo retorna, sino return null
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteDao.deleteById(id);
        
    }
    

}
