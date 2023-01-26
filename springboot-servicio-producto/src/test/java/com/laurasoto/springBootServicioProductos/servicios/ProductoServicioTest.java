package com.laurasoto.springBootServicioProductos.servicios;

import com.laurasoto.springBootServicioProductos.modelos.Producto;
import com.laurasoto.springBootServicioProductos.repositorios.ProductoRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductoServicioTest {

    @Test
    void dado_una_consulta_con_findById_retornamos_un_producto(){
        //arrange
        Producto producto = new Producto();
        ProductoRepositorio productoRepositorio = mock(ProductoRepositorio.class);
        ProductoServicio productoServicio = new ProductoServicio(productoRepositorio);
        producto.setNombre("LORITA");
        producto.setPrecio(400.00);
        when(productoRepositorio.findById(anyLong())).thenReturn(Optional.of(producto));

        //act
        Producto result = productoServicio.findById(3L);
        //asserts
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(producto.getNombre());
        Assertions.assertNotNull(producto.getPrecio());
        Assertions.assertEquals(producto.getNombre(), "LORITA");
        Assertions.assertEquals(producto.getPrecio(), 400.00);
    }

}