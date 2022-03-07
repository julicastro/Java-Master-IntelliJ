package com.security.repository;

import java.util.Optional;
import com.security.models.entity.Rol;
import com.security.models.entity.RolNombre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
    
    Optional <Rol> findByRolNombre(RolNombre rolNombre);

    Boolean existsByRolNombre(RolNombre rolNombre);
    /* Si el nombre no existe me va a tirar error */



}
