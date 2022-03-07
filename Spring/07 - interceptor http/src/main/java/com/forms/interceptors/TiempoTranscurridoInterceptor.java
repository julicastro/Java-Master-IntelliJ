package com.forms.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

    /*
     * llamamos al log para poder registrar algun evento en el log o loger de
     * nuestra app. en el log podemos ir imprimerdo el tiempo transcurrido
     */

    private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

    @Override // pre se ejecuta antes q el metodo del controller
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        /*
         * Object handler = el objeto q representa el metodo del controller q se esta
         * ejecutando
         */

        if (request.getMethod().equalsIgnoreCase("post")) {
            // si es el metodo post se aplica true para q se salga del interceptor
            // y asi solo aplique a los metodos GET
            return true;
        }

        logger.info("TiempoTranscurridoInterceptor: preHandle() entrando ...");
        logger.info("Interceptando: " + handler);
        String controllerUsado = handler.getClass().getName();
        request.setAttribute("controllerUsado", controllerUsado);
        long tiempoInicio = System.currentTimeMillis(); // calcula tiempo en mls
        request.setAttribute("tiempoInicio", tiempoInicio); // mombre y valor
        Random random = new Random();
        Integer demora = random.nextInt(500); // nos devuelve un random desde 0 a 500
        Thread.sleep(demora); // hace una pausa de tantos milisegundos

        /** En caso de q el return sea false: 
         * response.sendRedirect(request.getContextPath().concat("/login"));
         */

        return true;
        // true = continua con la ejecucion del controller
        // false = termina con la ejecucion del controller. Podemos redirigir
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // ModelandView nos permite pasar datos a la vista
        if (!request.getMethod().equalsIgnoreCase("post")) {
            // si es el metodo post se aplica true para q se salga del interceptor
            // y asi solo aplique a los metodos GET

            long tiempoFin = System.currentTimeMillis(); // calcula tiempo en mls
            long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
            String controllerUsado = (String) request.getAttribute("controllerUsado");
            // lo registramos en el pre con setAttribute y acá lo llamamos
            long tiempoTrancurrido = tiempoFin - tiempoInicio;
            // con modelAndView se lo pasa a la vista
            if (handler instanceof HandlerMethod && modelAndView != null) {
                modelAndView.addObject("tiempoTrancurrido", tiempoTrancurrido);
                modelAndView.addObject("controllerUsado", controllerUsado);
            }
            logger.info("Tiempo transcurrido: " + tiempoTrancurrido + " milisegundos");
            logger.info("TiempoTranscurridoInterceptor: posHandle() saliendo ...");
        }
    }

}
