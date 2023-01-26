package com.laurasoto.springboot.app.item.springbootservicioitem.modelos.servicio;

import com.laurasoto.springboot.app.item.springbootservicioitem.clientes.ProductoClienteRest;
import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.Item;
import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemServicio{
    @Autowired
    private ProductoClienteRest clienteFeign;

    @Override
    public List<Item> findAll() {

        return clienteFeign.listar().stream().map(pr -> new Item(pr,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(clienteFeign.detalle(id), cantidad) ;
    }
}
