package com.forms.editors;

import java.beans.PropertyEditorSupport;

import com.forms.services.IPaisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private IPaisService service;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {
        try {
            Integer id = Integer.parseInt(idString);
            this.setValue(service.obtenerPorId(id));
        } catch (NumberFormatException e) {
            setValue(null);
        }
    }

}
