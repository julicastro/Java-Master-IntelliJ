package com.forms.services;

import java.util.List;

import com.forms.models.domain.Pais;

public interface IPaisService {
    
    public List<Pais> listar();
    public Pais obtenerPorId(Integer id);



    
}
