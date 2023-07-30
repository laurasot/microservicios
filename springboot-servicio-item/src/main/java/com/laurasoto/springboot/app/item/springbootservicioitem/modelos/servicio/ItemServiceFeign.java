package com.laurasoto.springboot.app.item.springbootservicioitem.modelos.servicio;

import com.laurasoto.springboot.app.commons.modelos.Producto;
import com.laurasoto.springboot.app.item.springbootservicioitem.clientes.ProductoClienteRest;
import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.Item;
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

    @Override
    public Producto update(Producto producto, Long id) {
        return clienteFeign.uptdate(producto,id);
    }

    @Override
    public Producto save(Producto producto) {
        return clienteFeign.crear(producto);
    }

    @Override
    public void delete(Long id) {
        clienteFeign.eliminar(id);
    }
}
