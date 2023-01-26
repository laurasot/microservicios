package com.laurasoto.springboot.app.item.springbootservicioitem;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    @LoadBalanced
    //objeto manejado por el contenedor de spring
    public RestTemplate registrarRestTemplate(){
        //heramienta para hacer peticiones rest https (22 verbos)
        // siempre se necesita para hacer llamadas rest, a menos que se utilize la libreria feign
        return new RestTemplate();
    }
}
