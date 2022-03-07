package com.error.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.error.models.domain.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp implements UsuarioService {

    private List<Usuario> lista;

    public UsuarioServiceImp() {
        this.lista = new ArrayList<>();
        lista.add(new Usuario(1, "Julian", "Castro"));
        lista.add(new Usuario(2, "Lionel", "Messi"));
        lista.add(new Usuario(3, "Andres", "Iniesta"));
        lista.add(new Usuario(4, "Kun", "Aguero"));
        lista.add(new Usuario(5, "Dibu", "Martinez"));
        lista.add(new Usuario(6, "Paolo", "Rodriguez"));
        lista.add(new Usuario(7, "Pipo", "Fuentes"));
    }

    @Override
    public List<Usuario> listar() {
        return this.lista;
    }

    @Override
    public Usuario obtenerPorId(Integer id) {
        Usuario resultado = null;
        for (Usuario u : lista) {
            if (u.getId().equals(id)) {
                resultado = u;
                break;
            }
        }
        return resultado;
    }

    @Override
    public Optional<Usuario> obtenerPorIdOptional(Integer id) {
        Usuario usuario = this.obtenerPorId(id);
        return Optional.ofNullable(usuario);
        /*
         * el metodo "of" lo q hace es convertir el objeto usuario a un tipo opcional
         * este tolera null. si usuario es null va a retornar un empty(). de lo
         * contrario retorna un optinal
         */
    }

}
