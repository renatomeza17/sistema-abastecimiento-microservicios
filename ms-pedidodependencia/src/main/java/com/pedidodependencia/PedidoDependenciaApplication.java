package com.sudab.pedidodependencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class PedidoDependenciaApplication {
    public static void main(String[] args) {
        SpringApplication.run(PedidoDependenciaApplication.class, args);
    }
}
