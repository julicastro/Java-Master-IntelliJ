package com.forms.services;

import java.util.ArrayList;
import java.util.List;

import com.forms.models.domain.Role;

import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements IRoleService {

    private List <Role> roles;

    public RoleServiceImp(){
        this.roles = new ArrayList<>();
        this.roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
        this.roles.add(new Role(2, "Usuario", "ROLE_USER"));
        this.roles.add(new Role(3, "Moderador", "ROLE_MODERATOR"));
    }

    @Override
    public List<Role> listar() {
        return roles;
    }

    @Override
    public Role obtenerPorId(Integer id) {
        Role resultado = null;
        for (Role role : roles) {
            if(id == role.getId()){
                resultado = role;
                break;
            }
        }
        return resultado;
    }
    


}
