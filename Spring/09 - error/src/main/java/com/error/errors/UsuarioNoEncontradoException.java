package com.error.errors;

public class UsuarioNoEncontradoException extends RuntimeException {
   
    /* pide traer un serialVersionUID */
    private static final long serialVersionUID = 1L;

    public UsuarioNoEncontradoException (String id){
        super("Usuario con ID: ".concat(id).concat(" no existe en el sistema"));
    }



}
