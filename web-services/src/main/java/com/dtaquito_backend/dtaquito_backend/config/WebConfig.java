package com.dtaquito_backend.dtaquito_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Permitir el acceso desde este origen
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir estos métodos
                .allowedHeaders("*") // Permitir todos los headers
                .allowCredentials(true);
    }
}