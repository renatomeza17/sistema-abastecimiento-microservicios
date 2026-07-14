package com.sudab.pedidodependencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
@EnableFeignClients
public class PedidoDependenciaApplication {
    public static void main(String[] args) {
        SpringApplication.run(PedidoDependenciaApplication.class, args);
    }
}
