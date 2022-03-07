package com.inyeccion.models.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/* @Component               queda registrada como clase de Spring 
 tambien podemos usar @Service. Lo unico q cambia es la semantica. 
 Solo para indicar q se trata de logica de negicio de un service 
 */

//@Service("miServicioSimple")
//@Primary // servicio x defecto
public class MyService implements IMyService{
    /* miServicioSimple sirve para indicar q implementacion
    de la interfaz IMyService se va a ejecutar */
    
    @Override
    public String operacion(){
         return "Ejecutando proceso importante simple...";
    }


}
