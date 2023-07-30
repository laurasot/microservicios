package com.laurasoto.springboot.app.item.springbootservicioitem.modelos.servicio;

import com.laurasoto.springboot.app.commons.modelos.Producto;
import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.Item;

import java.util.List;

public interface ItemServicio {
    public List<Item> findAll();
    public Item findById(Long id, Integer cantidad);

    public Producto update(Producto producto, Long id);
    public Producto save(Producto producto);
    public void delete(Long id);
}
