package com.horario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    @Autowired
    @Qualifier("horarioInterceptor")
    private HandlerInterceptor horario;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(horario).excludePathPatterns("/cerrado"); 
        // excluimos cerrado para q no tire error
    }
    
}
