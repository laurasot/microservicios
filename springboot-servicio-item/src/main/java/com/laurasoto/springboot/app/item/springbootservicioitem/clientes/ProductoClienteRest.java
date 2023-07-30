package com.laurasoto.springboot.app.item.springbootservicioitem.clientes;

import com.laurasoto.springboot.app.commons.modelos.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//se indica el nombre del microservicio al que se quiere conectar
@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {
    @GetMapping("/listar")
    public List<Producto> listar();

    @GetMapping("/listar/{id}")
    public Producto detalle(@PathVariable Long id);

    @PostMapping("/crear")
    public Producto crear(@RequestBody Producto producto);

    @PutMapping("/editar/{id}")
    public Producto uptdate(@RequestBody Producto producto, @PathVariable Long id);

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id);


}
