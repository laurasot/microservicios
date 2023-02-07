package com.laurasoto.springboot.app.item.springbootservicioitem.controladores;

import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.Item;
import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.Producto;
import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.servicio.ItemServicio;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemControlador {
    @Autowired
    @Qualifier("serviceFeign")
    private ItemServicio itemServicio;
    /*@Autowired
    @Qualifier("servicioRestTemplate")
    private ItemServicio itemServicio;*/
    @GetMapping("/listar")
    public List<Item> listar(){
        return itemServicio.findAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlternativo") //deriva a otro metodo
    @GetMapping("/listar/{id}/cantidad/{cantidad}")
    public Item item(@PathVariable Long id, @PathVariable Integer cantidad){
        return itemServicio.findById(id, cantidad);
    }

    public Item metodoAlternativo(Long id, Integer cantidad){
        Item item = new Item();
        Producto producto = new Producto();

        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("sonycp");
        producto.setPrecio(400.00);
        item.setProducto(producto);
        return item;
    }
}
