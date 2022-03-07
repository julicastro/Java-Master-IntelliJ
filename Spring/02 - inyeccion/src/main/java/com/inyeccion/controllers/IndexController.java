package com.inyeccion.controllers;

import com.inyeccion.models.service.IMyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// @RequestMapping("/app")
public class IndexController {
    // el controller tambien interactua con el Service

    @Autowired /* Se usa para inyectar objeto registrado en el contenedor de Spring. */
    //@Qualifier("miServicioOtro") /* el nombre de la instancia de esta interfaz q usaremos */
    private IMyService miServicio; /* Recordemos q MyService fue registrado con la anotacion @Service en Spring */

    @GetMapping({ "/", "index", "home" })
    public String index(Model model) {

        model.addAttribute("objeto", miServicio.operacion());
        return "index";
    }

    public IMyService getMiServicio() {
        return this.miServicio;
    }

    /*
    @Autowired
    public IndexController(IMyService miServicio) {
        this.miServicio = miServicio;
    }// desde el constructor se puede omitir el autowired
    
    
    @Autowired
    public void setMiServicio(IMyService miServicio) {
        this.miServicio = miServicio;
    }
    */
    
}
