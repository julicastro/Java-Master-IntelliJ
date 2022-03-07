package com.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier("tiempoTranscurridoInterceptor")
    private HandlerInterceptor tiempoTranscurridoInterceptor;

    /**
     * podemos hacer q el interceptor solo se aplique a algunas rutas en
     * determinados controllers
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tiempoTranscurridoInterceptor).addPathPatterns("/form/**");
        // x ahora se aplica a todas las rutas.
        // con el method addPathPatterns("/form/**"); agregamos lo q queramos
        // /form/** == form y lo q siga dsp
    }

}
