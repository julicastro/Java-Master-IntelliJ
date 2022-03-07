package com.horario.interceptors;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("horarioInterceptor")
public class HorarioInterceptor implements HandlerInterceptor {

    @Value("${config.horario.apertura}")
    private Integer apertura;

    @Value("${config.horario.cierre}")
    private Integer cierre;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Calendar calender = Calendar.getInstance();
        int hora = calender.get(Calendar.HOUR_OF_DAY);
        /*
         * obtenemos hora del dia. al ser un singleton se obtiene una sola instancia
         * para toda la aplicacion
         */
        if (hora >= apertura && hora < cierre) {
            /*
             * string builder crea strings q pueden cambiar de instancia, se puede
             * concatenar y agregar sin crear nuevos objetos
             */
            StringBuilder mensaje = new StringBuilder("Bienvenido al horario de atencion al cliente");
            mensaje.append(", atendemos desde las ");
            mensaje.append(apertura);
            mensaje.append("hrs. ");
            mensaje.append("hasta las ");
            mensaje.append(cierre);
            mensaje.append("hrs. ");
            mensaje.append(". Gracias por su visita.");
            request.setAttribute("mensaje", mensaje.toString()); // lo convertimos todo a un simple String
            return true;
        }
        response.sendRedirect(request.getContextPath().concat("/cerrado"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        String mensaje = (String) request.getAttribute("mensaje");

        if (handler instanceof HandlerMethod && modelAndView != null) {
            modelAndView.addObject("horario", mensaje);

        }

    }

}
