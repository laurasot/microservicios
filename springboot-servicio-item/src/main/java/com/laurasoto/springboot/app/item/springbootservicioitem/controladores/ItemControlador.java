package com.laurasoto.springboot.app.item.springbootservicioitem.controladores;

import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.Item;
import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.servicio.ItemServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Qualifier("serviceFeign")
public class ItemControlador {
    @Autowired
    @Qualifier("servicioRestTemplate")
    private ItemServicio itemServicio;
    @GetMapping("/listar")
    public List<Item> listar(){
        return itemServicio.findAll();
    }

    @GetMapping("/listar/{id}/cantidad/{cantidad}")
    public Item item(@PathVariable Long id, @PathVariable Integer cantidad){
        return itemServicio.findById(id, cantidad);
    }
}
