package com.sudabRecepcion.ms_recepcion.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class EnvLoader {

    @PostConstruct
    public void loadEnv() {
        try (BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
            Map<String, String> envMap = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    envMap.put(parts[0].trim(), parts[1].trim());
                }
            }
            envMap.forEach((key, value) -> {
                if (System.getenv(key) == null) {
                    System.setProperty(key, value);
                }
            });
        } catch (IOException e) {
            System.out.println("No se encontro archivo .env, usando variables del sistema.");
        }
    }
}
