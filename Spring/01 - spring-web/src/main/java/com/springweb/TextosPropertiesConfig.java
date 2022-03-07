package com.springweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
    // aca podemos indicar varios archivos properties
    @PropertySource("classpath:textos.properties")
    // con , podemos seguir agregando mas 
})
public class TextosPropertiesConfig {
    // clase de configuracion del archivo "textos.properties"
}
