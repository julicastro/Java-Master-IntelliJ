package com.injection.models.service.domain;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
// @RequestScope // objeto se construye en cada peticion (en cada request)
// preDestroy se va a ejecutar en cada request y ya no solo una vez como en singleton
// @SessionScope /* muy usado en carritos de compras. DEBEMOS IMPLEMENTAR SEREALIZABLE */
// CON SESSION SCOPE NO APLICA EL PREDESTROY 
// debemos implementar el ID Unico de Serializable
// @ApplicationScope
@RequestScope
public class Factura implements Serializable{ 
    @Value("Factura Deporte")
    private String descripcion;

    @Autowired
    private Cliente cliente;

    @Autowired // con mas de una implementacion @Qualifier
    @Qualifier("itemsFactura")
    private List<ItemFactura> items;
        
    @PostConstruct // se ejecuta dsp de crear objeto e inyectar dependencia
    public void inicializar() {
        cliente.setNombre(cliente.getNombre().concat(" ".concat("Emanuel")));
        descripcion = descripcion.concat(" del cliente: ").concat(cliente.getNombre());
    }

    @PreDestroy // se ejecuta cuando se termina la app xq es una clase singleton. (cuando damos stop)
    public void destruir(){
        System.out.println("Factura destruida".concat(descripcion));
    }

    public Factura(String descripcion, Cliente cliente, List<ItemFactura> items) {
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.items = items;
    }

    public Factura(){
        
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFactura> getItems() {
        return this.items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }

}
