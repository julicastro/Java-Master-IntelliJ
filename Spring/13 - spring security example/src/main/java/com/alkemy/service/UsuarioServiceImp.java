package com.alkemy.service;

import com.alkemy.models.dao.IUsuarioDao;
import com.alkemy.models.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("usuarioServiceImp")
public class UsuarioServiceImp implements IUsuarioService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public Usuario findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    @Override
    public Usuario registrar(Usuario u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return usuarioDao.save(u);
    }

    
}
