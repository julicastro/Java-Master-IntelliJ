package com.security.repository;

import java.util.Optional;

import com.security.models.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Optional <Usuario> findByNombreUsuario(String nombreUsuario);
    /* Debe tener el mismo nombre que el atributo de Usuario */

    Boolean existsByNombreUsuario(String nombreUsuario);
    /* Si el nombre no existe me va a tirar error */



}
