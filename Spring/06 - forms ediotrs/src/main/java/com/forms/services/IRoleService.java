package com.forms.services;

import com.forms.models.domain.Role;
import java.util.List;


public interface IRoleService {

    public List <Role> listar();
    public Role obtenerPorId(Integer id);
}
