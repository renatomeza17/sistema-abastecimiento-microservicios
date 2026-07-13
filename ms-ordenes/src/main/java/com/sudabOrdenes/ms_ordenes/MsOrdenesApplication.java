package com.sudabOrdenes.ms_ordenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.sudabOrdenes.ms_ordenes.Config.EnvLoader;

@SpringBootApplication
@EnableFeignClients
public class MsOrdenesApplication {

	public static void main(String[] args) {
		EnvLoader.load();
		
		SpringApplication.run(MsOrdenesApplication.class, args);
	}

}
