package com.forms.models.domain;

public class Role {

    private Integer id;
    private String nombre;
    private String role;

    public Role(Integer id, String nombre, String role) {
        this.id = id;
        this.nombre = nombre;
        this.role = role;
    }

    public Role() {

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {// si esta instancia es = a los obj q se pasan x arg retorna true
            return true;
        }
        if (!(obj instanceof Role)) { // el obj x arg debe ser una instancia de role
            return false;
        }
        Role role = (Role) obj;
        return this.id != null && this.id.equals(role.getId());
    }
    // todo esto es para q thymeleaf pueda comparar si el rol q pasamos x defecto se
    // encuentra
    // en la lista q iteramos. Y asi lo mantenga
}
