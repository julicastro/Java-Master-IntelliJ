package com.forms.controllers;

import java.text.SimpleDateFormat; // recibe String y hace parse para Date
import java.util.Date;

import javax.validation.Valid;
import com.forms.models.domain.Usuario;
import com.forms.validation.UsuarioValidador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


@Controller
@SessionAttributes("usuario") // nombre del objeto el cual se pasa a la vista
// hace q se mantengan los datos independientemente de si está o no en el form
public class FormController {

    @Autowired
    private UsuarioValidador validador;

    @InitBinder // inicializamos validador para poder validar con @Valid
    public void InitBinder(WebDataBinder binder){
        // binder.setValidator(validador);
        // listo, ahora no necesito escribir nada mas q el @Valid 
        // lo unico es q todas las anotaciones se pierden
        // solo utiliza la de nuestra clase validadora
        // cambiamos el setValidator x el addValidator para agregar un nuevo al stack
        binder.addValidators(validador);
        // todo lo de abajo es una alternativa a @DateFormat
        // Captura el String y lo convierte a una fecha de Java
        // Pone el formato q querramos de forma autómatica
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // define si el analizador es estricto o tolerante. false = NO TOLERANTE
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false)); // false es q no permite vacios
        // igualmente es mas facil usar @DateFormat
    }

    @GetMapping("/form")
    public String form(Model model) {
        Usuario usuario = new Usuario();
        usuario.setNombre("Julian");
        usuario.setApellido("Castro");
        usuario.setIdentificador("125.123.654-K");
        usuario.setIdregular("12.123.654-K");
        model.addAttribute("titulo", "Crear nuevo usuario: ");
        model.addAttribute("usuario", usuario);
        return "form";
    }

    @PostMapping("form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) { 
        // validador.validate(usuario, result);// objeto, errors. BindingResult es del tipo Error
        model.addAttribute("titulo", "Resultado Form");
        if(result.hasErrors()){
            return "form"; // automatizamos los errores
        }
        model.addAttribute("usuario", usuario);
        status.setComplete(); // completa proceso y elimina el objeto Usuario de la sesion
        return "resultado";

    }
}
