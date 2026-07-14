package com.sudab.requerimientos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class RequerimientosProformasApplication {
    public static void main(String[] args) {
        SpringApplication.run(RequerimientosProformasApplication.class, args);
    }
}
