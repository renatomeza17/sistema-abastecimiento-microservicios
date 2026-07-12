package com.sudabRecepcion.ms_recepcion;

import com.sudabRecepcion.ms_recepcion.config.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MsRecepcionApplication {

	public static void main(String[] args) {
		EnvLoader.load();
		SpringApplication.run(MsRecepcionApplication.class, args);
	}

}
