package com.sudabKardex.ms_kardex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.sudabKardex.ms_kardex.Config.EnvLoader;

@SpringBootApplication
@EnableFeignClients
public class MsKardexApplication {

	public static void main(String[] args) {
		EnvLoader.load();
		SpringApplication.run(MsKardexApplication.class, args);
	}

}
