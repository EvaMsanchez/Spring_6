package com.eva.curso.springboot.app.interceptor.springboot_interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer
{
    // Inyectar el interceptor, le indicamos cual con el Qualifier
    @Autowired
    @Qualifier("timeInterceptor")
    private HandlerInterceptor timeInterceptor;


    // Para registrar o añadir el interceptor para que se aplique
    // Sino se especifica se aplica a TODAS las rutas, sino tenemos que indicar a cuales o las que queremos excluir
    @Override
    public void addInterceptors(InterceptorRegistry registry) 
    {
        registry.addInterceptor(timeInterceptor);
    }

}
