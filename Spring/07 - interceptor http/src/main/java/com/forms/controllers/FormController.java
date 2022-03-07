package com.forms.controllers;

import java.text.SimpleDateFormat; // recibe String y hace parse para Date
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.forms.editors.NombreMayusculaEditor;
import com.forms.editors.PaisPropertyEditor;
import com.forms.editors.RolesEditor;
import com.forms.models.domain.Pais;
import com.forms.models.domain.Role;
import com.forms.models.domain.Usuario;
import com.forms.services.IPaisService;
import com.forms.services.IRoleService;
import com.forms.services.RoleServiceImp;
import com.forms.validation.UsuarioValidador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("usuario") // nombre del objeto el cual se pasa a la vista
// hace q se mantengan los datos independientemente de si está o no en el form
public class FormController {

    @Autowired
    private UsuarioValidador validador;

    @Autowired
    private IPaisService paisService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PaisPropertyEditor paisEditor;

    @Autowired
    private RolesEditor roleEditor;

    @InitBinder // inicializamos validador para poder validar con @Valid
    public void InitBinder(WebDataBinder binder) {
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
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        // false es q no permite // vacios
        // igualmente es mas facil usar @DateFormat
        // registrar editor para convertir a mayuscula
        binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
        binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
        // el segundo parametro es a q atributo le queremos aplicar la mayuscula

        binder.registerCustomEditor(Pais.class, "pais", paisEditor);
        binder.registerCustomEditor(Role.class, "roles", roleEditor);

    }

    @GetMapping("/form")
    public String form(Model model) {
        Usuario usuario = new Usuario();
        usuario.setNombre("Julian");
        usuario.setApellido("Castro");
        usuario.setIdentificador("125.123.654-K");
        usuario.setIdregular("12.123.654-K");
        usuario.setHabilitar(true);
        usuario.setPais(new Pais(2, "AR", "Argentina"));
        usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER")));
        usuario.setValorSecreto("Soy un terrible valor secreto :)");
        model.addAttribute("titulo", "Crear nuevo usuario: ");
        model.addAttribute("usuario", usuario);
        return "form";
    }

    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {
        // validador.validate(usuario, result);// objeto, errors. BindingResult es del
        // tipo Error
        model.addAttribute("titulo", "Resultado Form");
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Resultado Form");

            return "form"; // automatizamos los errores
        }
        // model.addAttribute("usuario", usuario);
        // status.setComplete(); // completa proceso y elimina el objeto Usuario de la
        // sesion

        return "redirect:/ver"; // redirige hacia /ver, para q no se reenvien los datos
    }// al redirigir realiza un nuevo request

    @GetMapping("/ver")
    public String ver(@SessionAttribute(name="usuario", required = false) Usuario usuario, Model model, SessionStatus status) {
        if(usuario == null){
            return "redirect:/form";
        }
        // @SessionAttribute significa q obtiene al usuario desde la sesion
        // inyectamos al usuario q está guardado con ese nombre "usuario"
        /*
         * al estar en el sessionAttribute tmb está en el modelAttribute,
         * x lo q no es necesario pasarlo a la vista
         */
        // model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Resultado Form");
        return "resultado";
        // ahora cuando refresco la vista resultado ya no se reenvian los datos.
        // no estamos mas dentro del post x lo q no se reenvia el form.
        // ahora estamos dentro del get x lo q solo se obtienen los datos
    }

    // sirve para los Select en forms
    @ModelAttribute("paises") // este es el nombre con el q se pasa a la vista
    public List<String> paises() {
        return Arrays.asList("Argentina", "Chile", "Uruguay", "Canada", "Peru", "España");
    }

    @ModelAttribute("paisesMap")
    public Map<String, String> paisesMap() {
        Map<String, String> paises = new HashMap<>();
        paises.put("ES", "España");
        paises.put("MX", "Mexico");
        paises.put("AR", "Argentina");
        paises.put("CH", "Chile");
        paises.put("PE", "Peru");
        paises.put("CO", "Colombia");
        paises.put("VE", "Venezuela");
        return paises;
    }

    @ModelAttribute("listaPaises") // este es el nombre con el q se pasa a la vista
    public List<Pais> listaPaises() {
        return paisService.listar();
    }

    @ModelAttribute("listaRolesString")
    public List<String> listarRolesString() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        roles.add("ROLE_MODERATOR");
        return roles;
    }

    @ModelAttribute("rolesMap")
    public Map<String, String> rolesMap() {
        Map<String, String> paises = new HashMap<>();
        paises.put("ROLE_ADMIN", "Administrador");
        paises.put("ROLE_USER", "Usuario");
        paises.put("ROLE_MODERATOR", "Moderador");
        return paises;
    }

    @ModelAttribute("listaRoles")
    public List<Role> listaRoles() {
        return this.roleService.listar();
    }

    @ModelAttribute("genero")
    public List<String> genero() {
        return Arrays.asList("Hombre", "Mujer");
    }

}
