package com.forms.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.forms.models.domain.Usuario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class FormController {

    /* necesitamos metodo para obtener (get) y otro para procesar (post) */

    @GetMapping("/form")
    public String form(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("titulo", "Crear nuevo usuario: ");
        model.addAttribute("usuario", usuario); // para q no sea null creamos el objeto
        return "form";
    }

    // post envia los datos para procesar o recibir el formulario.
    // luego obtememos los resultados con get
    // através de post nosostros obtememos los datos. y con get los mostramos
    // mismo nombre q el name del input
    /*
    @PostMapping("form")
    public String procesar(Model model, @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password, @RequestParam String email) {
        // como se llama igual el nombre del argumento se puede omitir el (name =
        // "username")
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);
        model.addAttribute("username", username);
        model.addAttribute("usuario", usuario);
        return "resultado";
    }
    */

    // mejorando el metodo anterior
    @PostMapping("form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model) { 
        // despues de poner @Valid hay q definir las reglas de validacion en la clase Usuario
        // BindingResult = objeto propio de Spring. es el result de la validacion 
        // debe estar dsp del objeto q se valida (Usuario). Debe ser el 2do arg
        model.addAttribute("titulo", "Resultado Form");
        if(result.hasErrors()){
            Map <String, String> errores  = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "El campo ".concat(err.getField().concat(" ")
                .concat(err.getDefaultMessage())));
            });;
            model.addAttribute("error", errores);
            return "form"; // podemos retornar pantalla de error
        }
        model.addAttribute("usuario", usuario);
        return "resultado";
        // funciona exactamente igual q el metodo anterior
    }
}
