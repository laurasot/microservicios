package com.laurasoto.springBootServicioProductos.servicios;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laurasoto.springBootServicioProductos.modelos.Producto;
import com.laurasoto.springBootServicioProductos.repositorios.ProductoRepositorio;
@AllArgsConstructor
@Service

public class ProductoServicio implements IproductoServicio{
	@Autowired
	private ProductoRepositorio productoRepositorio;

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoRepositorio.findAll();
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public Producto findById(Long id) {
		// TODO Auto-generated method stub
		return productoRepositorio.findById(id).orElse(null);
	}

}
