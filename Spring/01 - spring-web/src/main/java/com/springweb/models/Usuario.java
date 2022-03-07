package com.springweb.models;

import java.util.Objects;

import lombok.Data;

@Data
public class Usuario {
    
    private String nombre;

    private String apellido;

    private String email;

    public Usuario(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public Usuario() {
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre) && Objects.equals(apellido, usuario.apellido) && Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, email);
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }





}
