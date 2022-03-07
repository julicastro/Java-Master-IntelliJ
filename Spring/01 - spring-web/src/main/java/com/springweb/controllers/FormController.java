package com.springweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {
    
    // utilizamos el metodo REDIRECT

    
    @GetMapping("/redirect")
    public String home(){
        // cambian los parametros de la url
        return "redirect:/app/index";
    }
    
    @GetMapping("/forward")
    public String homeForward(){
        // en este caso no cambian los parametros de la url 
        return "forward:/app/index"; // se usa para paginas internas
        // ejecuta x debajo un requestDispatcher del API Servlet y el metodo forward()
    }

    @GetMapping("/google")
    public String googleRedirect(){
        // fordward no recarga la pagina x lo q los parametros del request se quedan
        return "redirect:http://www.google.com";
    }

    





}
