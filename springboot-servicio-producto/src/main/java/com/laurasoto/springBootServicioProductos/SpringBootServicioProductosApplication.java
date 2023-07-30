package com.laurasoto.springBootServicioProductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan({"com.laurasoto.springboot.app.commons.modelos"})
@EnableEurekaClient
public class SpringBootServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootServicioProductosApplication.class, args);
	}

}
