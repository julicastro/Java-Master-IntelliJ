package com.forms.services;

import java.util.Arrays;
import java.util.List;
import com.forms.models.domain.Pais;
import org.springframework.stereotype.Service;

@Service
public class PaisServiceImp implements IPaisService {

    private List<Pais> lista;

    public PaisServiceImp() {
        this.lista = Arrays.asList(
                new Pais(1, "ES", "España"),
                new Pais(2, "AR", "Argentina"),
                new Pais(3, "CH", "Chile"),
                new Pais(4, "CO", "Colombia"),
                new Pais(5, "PE", "Peru"),
                new Pais(6, "UR", "Uruguay"));
    }

    @Override
    public List<Pais> listar() {
        return lista;
    }

    @Override
    public Pais obtenerPorId(Integer id) {
        Pais resultado = null;
        for(Pais pais: this.lista){
            if(id == pais.getId()){
                resultado = pais;
                break;
            }
        }
        return resultado;
    }

}
