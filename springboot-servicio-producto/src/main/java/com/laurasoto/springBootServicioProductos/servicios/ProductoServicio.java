package com.laurasoto.springboot.app.servicioproductos.servicios;

import java.util.List;

import com.laurasoto.springboot.app.servicioproductos.repositorios.ProductoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laurasoto.springboot.app.commons.modelos.Producto;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoRepositorio.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		productoRepositorio.deleteById(id);
	}

}
