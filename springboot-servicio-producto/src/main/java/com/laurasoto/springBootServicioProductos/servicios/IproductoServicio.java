package com.laurasoto.springBootServicioProductos.servicios;

import java.util.List;

import com.laurasoto.springBootServicioProductos.modelos.Producto;

public interface IproductoServicio {
	public List<Producto> findAll();
	public Producto findById(Long id);
}
