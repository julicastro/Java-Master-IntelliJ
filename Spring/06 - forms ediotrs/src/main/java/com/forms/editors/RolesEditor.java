package com.forms.editors;

import java.beans.PropertyEditorSupport;

import com.forms.services.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RolesEditor extends PropertyEditorSupport{
    
    @Autowired
    private IRoleService service;

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
