package com.crud.controllers;

import java.util.Map;

import javax.validation.Valid;

import com.crud.models.entity.Cliente;
import com.crud.service.IClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("cliente") /* para guardar objeto con sus datos en la session */
/* mismo nombre q el objeto q se pasa */
public class ClienteController {

    @Autowired // busca un bean q implemente dicha interfaz
    private IClienteService clienteService;

    // LISTAR

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteService.findAll());
        return null;
    }

    // CREAR

    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        // valor del tipo object para poder crear cualquier objeto
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de cliente");
        model.put("botonSubmit", "Crear Cliente");

        return "form";
    }

    // metodo encargado de insertar datos cuando usuario da submit
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash,
            SessionStatus status) {
        /*
         * @Valid para q tome las validaciones de la clase entidad.
         * RequestMethod tambien es para validar. Siempre va junto al objeto
         * del formulario. despues los demas parametros.
         * el tipo de la clase "cliente" debe llamarse igual q el nombre con el q lo
         * pasamos a la vista. por mas q empiece en minuscula. asi se pasa
         * automaticamente. en caso de tener nombres distintos debemos pasar x parametro
         * 
         * @ModelAttribute. en esta caso modelAttribute no es necesario
         * 
         * @ModelAttribute("cliente")
         */

        status.setComplete(); // elimina la session. termina el proceso xq ya tenemos los datos
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de cliente");
            return "form";
        }
        clienteService.save(cliente);
        flash.addFlashAttribute("succes", "Cliente creado con exito");
        return "redirect:listar";
    }

    // EDITAR

    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        /*
         * @PathVariable es una anotacion para q no inyecte el valor del parametro de la
         * ruta
         */
        Cliente cliente = null;
        if (id > 0) {
            cliente = clienteService.findOne(id);
            if (cliente == null) {
                flash.addFlashAttribute("error", "El id del cliente no existe en la base de datos");
                return "redirect/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El id del cliente no puede ser 0");
            return "redirect/listar";
        }
        model.put("cliente", cliente);
        flash.addFlashAttribute("succes", "Cliente modificado con exito");
        model.put("titulo", "Editar Cliente");
        model.put("botonSubmit", "Guardar");
        return "form";
    }

    // ELIMINAR

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            clienteService.delete(id);
            flash.addFlashAttribute("succes", "Cliente eliminado con exito");
        }
        return "redirect:/listar";
    }

}
