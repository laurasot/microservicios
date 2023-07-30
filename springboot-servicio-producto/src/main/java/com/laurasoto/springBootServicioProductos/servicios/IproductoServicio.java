package com.laurasoto.springBootServicioProductos.servicios;

import java.util.List;

import com.laurasoto.springboot.app.commons.modelos.Producto;

public interface IproductoServicio {
	public List<Producto> findAll();
	public Producto findById(Long id);
	public Producto save(Producto producto);

	public void deleteById(Long id);
}
