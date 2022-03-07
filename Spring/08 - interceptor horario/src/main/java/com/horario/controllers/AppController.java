package com.horario.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Value("${config.horario.apertura}")
    private Integer apertura;

    @Value("${config.horario.cierre}")
    private Integer cierre;

    @GetMapping({ "/", "/index" })
    public String index(Model model) {
        model.addAttribute("titulo", "Bienvenido al horario de atencion a clientes");
        return "index";
    }

    @GetMapping("/cerrado")
    public String cerrado(Model model) {
        StringBuilder mensaje = new StringBuilder("Cerraro. El horario de atencion es: ");
        mensaje.append("desde las ");
        mensaje.append(apertura);
        mensaje.append("hrs. ");
        mensaje.append("hasta las ");
        mensaje.append(cierre);
        mensaje.append("hrs. ");
        mensaje.append("Gracias por su visita.");
        model.addAttribute("titulo", "Estás fuera del horario de atención");
        model.addAttribute("mensaje", mensaje.toString());

        return "cerrado";
    }
}
