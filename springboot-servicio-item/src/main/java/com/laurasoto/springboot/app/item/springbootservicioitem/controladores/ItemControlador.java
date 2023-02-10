package com.laurasoto.springboot.app.item.springbootservicioitem.controladores;

import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.Item;
import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.Producto;
import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.servicio.ItemServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemControlador {
    private final Logger logger = LoggerFactory.getLogger(ItemControlador.class);
    @Autowired
    private CircuitBreakerFactory cbFactory;
    @Autowired
    @Qualifier("serviceFeign")
    private ItemServicio itemServicio;
    /*@Autowired
    @Qualifier("servicioRestTemplate")
    private ItemServicio itemServicio;*/
    @GetMapping("/listar")
    public List<Item> listar(@RequestParam (name = "nombre", required= false) String nombre,
                             @RequestHeader(name = "token-request", required= false) String token){
        System.out.println(nombre);
        System.out.println(token);
        return itemServicio.findAll();
    }

    //@HystrixCommand(fallbackMethod = "metodoAlternativo") //deriva a otro metodo
    @GetMapping("/listar/{id}/cantidad/{cantidad}")
    public Item item(@PathVariable Long id, @PathVariable Integer cantidad){
        return cbFactory.create("items")
            .run(() -> itemServicio.findById(id, cantidad) , e -> metodoAlternativo(id, cantidad, e));
    }

    public Item metodoAlternativo(Long id, Integer cantidad, Throwable e){
        logger.info(e.getMessage());
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
