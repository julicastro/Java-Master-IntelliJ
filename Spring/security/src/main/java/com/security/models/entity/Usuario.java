package com.security.models.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name="usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String nombreUsuario;

    @NotNull
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", 
               joinColumns = @JoinColumn(name = "usuario_id"), 
               inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();



}
