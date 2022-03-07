package com.injection;

import java.util.ArrayList;
import java.util.List;

import com.injection.models.service.domain.ItemFactura;
import com.injection.models.service.domain.Producto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean("itemsFactura")
    public List <ItemFactura> registrarItems(){
        List <ItemFactura> items = new ArrayList<>();
        Producto p1 = new Producto("Monitor 24 pulgadas", 2500);
        Producto p2 = new Producto("Placa de video ASUS", 5400);
        ItemFactura linea1 = new ItemFactura(p1, 7);
        ItemFactura linea2 = new ItemFactura(p2, 3);
        items.add(linea1);
        items.add(linea2);
        return items;
    }

    @Bean("registrarItemsOficina")
    public List <ItemFactura> registrarItemsOficina(){
        List <ItemFactura> items = new ArrayList<>();
        Producto p1 = new Producto("Escritorio re fachero", 4570);
        Producto p2 = new Producto("Lamparas re piolas", 1790);
        Producto p3 = new Producto("Notebook Gigabyte", 10500);
        ItemFactura linea1 = new ItemFactura(p1, 5);
        ItemFactura linea2 = new ItemFactura(p2, 9);
        ItemFactura linea3 = new ItemFactura(p3, 4);
        items.add(linea1);
        items.add(linea2);
        items.add(linea3);
        return items;
    }


}
