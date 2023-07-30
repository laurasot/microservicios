package com.laurasoto.springboot.app.item.springbootservicioitem.modelos;

import com.laurasoto.springboot.app.commons.modelos.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Producto producto;
    private Integer cantidad;
    public Double getTotal(){
        return producto.getPrecio() * cantidad.doubleValue();
    }
}
