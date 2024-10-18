package com.eva.curso.springboot.app.interceptor.springboot_interceptor.interceptors;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor
{
    // Constante para el Log
    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // Objeto del controlador
        HandlerMethod controller = ((HandlerMethod)handler);
        logger.info("LoadingTimeInterceptor: preHandler() entrando ...." + controller.getMethod().getName()); // concatener el nombre del método que se está invocando a través de la URL
                
        long start = System.currentTimeMillis(); // fecha inicial del sistema
        request.setAttribute("start", start);

        // Simular una tardanza o demora de tiempo, para hacer luego la comprobación de cuanto tarda en cargar la página
        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);
        return true; 
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        
        long end = System.currentTimeMillis(); // fecha final del sistema
        long star = (long) request.getAttribute("start"); // cogemos la fecha inicial
        long result = end - star;
        logger.info("Tiempo transcurrido:" + result + "milisegundos!");

        logger.info("LoadingTimeInterceptor: postHandler() saliendo ...." + ((HandlerMethod)handler).getMethod().getName());
    }

   

}
