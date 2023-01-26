package com.laurasoto.springboot.app.item.springbootservicioitem.modelos.servicio;

import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.Item;

import java.util.List;

public interface ItemServicio {
    public List<Item> findAll();
    public Item findById(Long id, Integer cantidad);
}
