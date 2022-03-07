package com.alkemy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {
    
    @GetMapping("/index")
    private String index(){
        return "salida";
    }

}
