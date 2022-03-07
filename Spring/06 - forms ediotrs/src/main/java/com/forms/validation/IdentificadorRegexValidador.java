package com.forms.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// todo esto es para aplicar nuestra annotation para validar.
// pasamos del tipo string xq vamos a validad el id q es String
public class IdentificadorRegexValidador implements ConstraintValidator<IdentificadorRegex, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][a-zA-Z]{1}")) {
            return true;
        }
        return false;
    }

}
