package com.forms.models.domain;

import javax.validation.constraints.NotEmpty;

public class Pais {

    // siempre se envia el valor q estamos mapeaando
    // SE VALIDA EL OBJETO COMPLETO EN USUARIO CON @NOTNULL

    // @NotNull
    private Integer id;

    // @NotEmpty
    private String codigo;

    private String nombre;

    public Pais(Integer id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Pais() {

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.id.toString(); // retorna id como string
        /*
         * esto permite a thymeleaf ver cuando el id de la lista select coincide con
         * el id del pais q tenemos seleccionado x defecto en el metodo get
         */
    }

}
