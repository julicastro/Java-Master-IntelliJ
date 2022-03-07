package com.forms.validation;

import com.forms.models.domain.Usuario;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UsuarioValidador implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Usuario usuario = (Usuario) target;
        // errors; nombre del atributo a validar; mensaje de error registrado en el properties
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "requerido.usuario.nombre"); 
        /* // Otra manera:
        if(usuario.getNombre().isEmpty()){
            errors.rejectValue("nombre", "NotEmpty.usuario.nombre");
        }
        */
        /*if(usuario.getIdregular().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][a-zA-Z]{1}") == false){
            errors.rejectValue("idregular", "pattern.usuario.idregular");
        }*/ // lo comentamos xq ahora lo estamos validando con anotaciones

        /* Asi podemos validar todos los campos q querramos */
    }

}
