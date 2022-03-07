package com.springweb.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class EjemploParamsControl {

    @GetMapping("/")
    public String index() {
        return "params/index";
    }

    @GetMapping("/string")
    public String param(
            @RequestParam(name = "texto", required = false, defaultValue = "Soy un valor por defecto") String texto,
            Model model) {
        /*
         * la idea es pasar parametros mediante la url
         * quiero obtener un parametro el cual podemos capturar con @RequestParam
         * capturamos el parametro y lo mostramos en la vista "ver"
         */
        model.addAttribute("resultado", "El texto enviado es: " + texto);
        return "params/ver";
    }

    @GetMapping("/mix-params")
    public String param(@RequestParam String saludo, @RequestParam Integer numero, Model model) {
        model.addAttribute("resultado", "El saludo enviado es: '" + saludo + "' , y le número es '" + numero + "'");
        return "params/ver";
    }

    @GetMapping("/mix-params-request")
    public String param(HttpServletRequest request, Model model) {
        String saludo = request.getParameter("saludo");
        // parse int xq HttpServletRequest devuelve un Integer
        // parseInt conviene usar el try - catch
        Integer numero = null;
        try{
            numero = Integer.parseInt(request.getParameter("numero")); 
        }catch (NumberFormatException e){
            numero = 0;
        }
        model.addAttribute("resultado", "El saludo enviado es: '" + saludo + "' , y le número es '" + numero + "'");
        return "params/ver";
    }

}
