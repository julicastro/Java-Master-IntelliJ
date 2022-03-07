package com.alkemy.controllers;

import javax.servlet.http.HttpSession;

import com.alkemy.models.entity.Usuario;
import com.alkemy.service.IUsuarioService;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private")
public class PrivateController {
    
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/index")
    public String index(Authentication auth, HttpSession session){{}
        String username = auth.getName();

        if(session.getAttribute("usuario") == null){
            Usuario usuario = usuarioService.findByUsername(username);
            usuario.setPassword(null);
            session.setAttribute("usuario", usuario);
        }
        return "index";
    }



}
