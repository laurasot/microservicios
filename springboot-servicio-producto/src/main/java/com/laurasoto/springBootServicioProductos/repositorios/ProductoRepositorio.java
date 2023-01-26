package com.laurasoto.springBootServicioProductos.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laurasoto.springBootServicioProductos.modelos.Producto;
@Repository
public interface ProductoRepositorio extends CrudRepository<Producto, Long> {

}
