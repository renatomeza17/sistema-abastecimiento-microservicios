package com.sudabLogin.ms_login;

import com.sudabLogin.ms_login.config.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MsLoginApplication {

	public static void main(String[] args) {
		EnvLoader.load();
		SpringApplication.run(MsLoginApplication.class, args);
	}

}