package com.injection.models.service.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemFactura {


    private Producto producto;

    private Integer cantidad;

    public ItemFactura(Producto producto, Integer cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public ItemFactura(){

    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer calcularImporte(){
        return cantidad * producto.getPrecio();
    }

}
