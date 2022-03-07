package com.inyeccion;

import java.util.Arrays;
import java.util.List;

import com.inyeccion.models.service.IMyService;
import com.inyeccion.models.service.MyService;
import com.inyeccion.models.service.MyServiceOtro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {
    
    @Bean("miServicioSimple")
    public IMyService registrarMyServicioSimple(){
        return new MyService();
    }

    @Bean("miServicioOtro")
    @Primary
    public IMyService registrarMyServicioComplejo(){
        return new MyServiceOtro();
    }



}
