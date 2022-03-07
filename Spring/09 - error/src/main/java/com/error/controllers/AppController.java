package com.error.controllers;

import com.error.errors.UsuarioNoEncontradoException;
import com.error.models.domain.Usuario;
import com.error.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AppController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping({ "/index", "/" })
    public String index() {
        // Integer valor = 100/0; // da ArithmeticException
        Integer valor = Integer.parseInt("10x"); // NumberFormatException
        return "index";
    }
    /*
    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        if(usuario == null){
            throw new UsuarioNoEncontradoException(id.toString());
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Detalle usuario: ".concat(usuario.getNombre()));
        /**
         * si no encuentra al usuario x id va a retornar null. si retorna null, el
         * usuario.getNombre() va a dar un NullPointerException
         * debemos manejar la excepcion
         */
        /*
        return "ver";
    }*/

    @GetMapping("/ver/{id}")
    public String verConAOptional(@PathVariable Integer id, Model model) {
        // Usuario usuario = usuarioService.obtenerPorId(id);
        /*if(usuario == null){
            throw new UsuarioNoEncontradoException(id.toString());
        }*/ 
        Usuario usuario = usuarioService.obtenerPorIdOptional(id).orElseThrow(() -> new UsuarioNoEncontradoException(id.toString()));
        // orElseThrow() lanza una excepcion en caso de q objeto sea empty (null)
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Detalle usuario: ".concat(usuario.getNombre()));
        /**
         * si no encuentra al usuario x id va a retornar null. si retorna null, el
         * usuario.getNombre() va a dar un NullPointerException
         * debemos manejar la excepcion
         */
        return "ver";
    }

}
