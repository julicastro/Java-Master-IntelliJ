package com.injection.controllers;

import com.injection.models.service.domain.Factura;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private Factura factura;
    
    @GetMapping("/ver")
    public String ver(Model model){
        model.addAttribute("factura", factura);
        model.addAttribute("titulo", "Ejemplo Factura con Inyeccion de Dependencia");
        return "factura/ver";
    }
}
