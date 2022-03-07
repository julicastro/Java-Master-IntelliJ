package com.error.controllers;

import java.util.Date;

import com.error.errors.UsuarioNoEncontradoException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ControllerAdvice maneja excepeciones. las captura y da la posibilidad de
 * manejarlos en esta clase
 */
@ControllerAdvice
public class ErrorHandlerController {
    /** esta clase la usamos para manejar muchos tipos de erro de java */
    /** los metodos se mapeasn a una excepcion y no a una ruta url */

    @ExceptionHandler(ArithmeticException.class)
    public String rithmeticException(Exception ex, Model model) {
        model.addAttribute("error", "Error de aritmética");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); // retorna 500
        model.addAttribute("timestamp", new Date());
        // return "error/aritmetica";
        return "error/generica";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String numberFormatException(Exception ex, Model model) {
        model.addAttribute("error", "Error de formato");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); // retorna 500
        model.addAttribute("timestamp", new Date());
        // return "error/numero-formato";
        return "error/generica";
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public String usuarioNoEncontradoException(Exception ex, Model model) {
        model.addAttribute("error", "Error: el usuario no existe");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); // retorna 500
        model.addAttribute("timestamp", new Date());
        return "error/usuario";
        /*
         * podemos crear nuestra propia vista para este error q creamos nosotros
         */
    }

}
