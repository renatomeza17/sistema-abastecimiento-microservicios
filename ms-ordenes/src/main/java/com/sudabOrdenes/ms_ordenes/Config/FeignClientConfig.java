package com.sudabOrdenes.ms_ordenes.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration
public class FeignClientConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        // Esto añade automáticamente la cabecera de autenticación/pasarela 
        // para que otros microservicios confíen en las peticiones de ms-ordenes
        return requestTemplate -> requestTemplate.header("X-Gateway-Request", "true");
    }

}
