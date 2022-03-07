package com.crud.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
/* Entity indica q es una clase pojo q va a estar mapeada a una tabla */
@Table(name = "clientes")
/*
 * Table es para configurar el nombre de la tabla. debe ser igual q la tabla de
 * la bdd
 */
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
     * Serializacion es el proceso de convertir objeto en secuencia de bytes
     * para almacenarlo o transmitirlo a la memoria o a donde sea
     */

    @Id // indica q es la pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* indicamos cual es el motor, la estrategia q va a generar la pk */
    private Long id;

    // @Column(name="nombre_cliente", nullable = false, unique = true)
    @NotEmpty
    @Size(min=4, max=15)
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    @Email
    private String email;

    @Column(name = "create_at") // asi x convencions
    @Temporal(TemporalType.DATE) // indica el formato en q se guarda la fecha de java en la tabla de la bdd
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private Date createAt; 

    /*
     * la anotacion entity x defecto va a mapear los atributos a un campo q se llame
     * exactamnte igual. x lo q no haria falta mapear nada. si se llaman distinto
     * con Column le indicamos el nombre
     */

    /*
    @PrePersist // se invoca este metodo justo antes de insertar el registro
    public void prePersist() { // se agrega sola sin form
        createAt = new Date();
    }
    */
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

}
