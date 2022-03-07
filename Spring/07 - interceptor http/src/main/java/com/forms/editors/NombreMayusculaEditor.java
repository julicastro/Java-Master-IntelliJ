package com.forms.editors;

import java.beans.PropertyEditorSupport;
import org.springframework.stereotype.Component;

@Component
public class NombreMayusculaEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.toUpperCase().trim()); // trim quita espacios en blanco
    }

}
