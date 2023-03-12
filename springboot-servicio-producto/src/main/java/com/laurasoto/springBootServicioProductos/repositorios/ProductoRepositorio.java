package com.laurasoto.springboot.app.servicioproductos.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laurasoto.springboot.app.commons.modelos.Producto;
@Repository
public interface ProductoRepositorio extends CrudRepository<Producto, Long> {

}
