package com.laurasoto.springboot.app.item.springbootservicioitem.clientes;

import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//se indica el nombre del microservicio al que se quiere conectar
@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {
    @GetMapping("/listar")
    public List<Producto> listar();

    @GetMapping("/listar/{id}")
    public Producto detalle(@PathVariable Long id);

}
