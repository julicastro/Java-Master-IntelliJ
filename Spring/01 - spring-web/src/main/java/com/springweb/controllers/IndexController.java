package com.springweb.controllers;

import java.util.ArrayList;
import java.util.List;

import com.springweb.models.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app") // ruta q hace referencia a todas las rutas/metodos de este controller
public class IndexController {

    @Value("${texto.indexcontroller.index.titulo}")
    private String textoIndex;

    @Value("${texto.indexcontroller.index.perfil}")
    private String textoPerfil;

    @Value("${texto.indexcontroller.index.listar}")
    private String textoListar;

    // @RequestMapping(value="/index", method = RequestMethod.GET)
    // esta forma da el mismo resultado
    // @GetMapping("/index") // x default es get
    @GetMapping({ "/index", "/", "/home", "" }) // x default es get
    public String index(Model model) {
        // model es un map x lo q recibe key/value
        model.addAttribute("titulo", this.textoIndex);
        return "index"; // la vista q retorna
    }

    @RequestMapping("/perfil")
    public String perfil(Model model) {
        Usuario usuario = new Usuario();
        usuario.setNombre("Julian");
        usuario.setApellido("Castro");
        usuario.setEmail("castrojulian@mail.com");
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", this.textoPerfil.concat(usuario.getNombre()));
        return "perfil";
    }

    @RequestMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("titulo", this.textoListar);
        return "listar";
    }

    @ModelAttribute("usuarios") // clave
    public List<Usuario> poblarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        /*
         * gracias al modelAttribute, el "usuarios" es la clave
         * de este metodo y lo q retorna el mismo es el valor.
         * esto es equivalente a addAttribute pero afecta a todos
         * los metodos de esta vista
         * cualquier vista puede mostrar el contenido de este metodo
         */
        usuarios.add(new Usuario("Julian", "Castro", "mail1"));
        usuarios.add(new Usuario("Ethan", "Winters", "mail12"));
        usuarios.add(new Usuario("Leon", "Refield", "mail13"));
        return usuarios; // valor
    }

}
