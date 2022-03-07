package com.security.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import com.security.models.entity.Usuario;
import com.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List <Usuario> lista(){
        return usuarioRepository.findAll();
    }
    
    public Optional <Usuario> getById(Long id){
        return usuarioRepository.findById(id);
    }

    public Optional <Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public void save (Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public Boolean existsById(Long id){
        return usuarioRepository.existsById(id);
    }

    public Boolean existsByNombreUusuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

}
