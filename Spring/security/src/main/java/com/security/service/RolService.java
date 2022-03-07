package com.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.security.models.entity.Rol;
import com.security.models.entity.RolNombre;
import com.security.repository.RolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RolService {
    
     @Autowired
     private RolRepository rolRepository;

     public void save (Rol rol){
         rolRepository.save(rol);
     }

     public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

    public boolean existsByRolNombre(RolNombre rolNombre){
        return rolRepository.existsByRolNombre(rolNombre);
    }

}
