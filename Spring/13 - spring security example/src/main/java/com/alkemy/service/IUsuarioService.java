package com.alkemy.service;

import com.alkemy.models.entity.Usuario;

public interface IUsuarioService {
    
    public Usuario findByUsername(String username);
    public Usuario registrar(Usuario usuario);

}
