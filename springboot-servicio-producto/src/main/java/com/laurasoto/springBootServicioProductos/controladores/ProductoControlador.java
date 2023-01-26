package com.laurasoto.springBootServicioProductos.controladores;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.laurasoto.springBootServicioProductos.modelos.Producto;
import com.laurasoto.springBootServicioProductos.servicios.IproductoServicio;

@RestController
public class ProductoControlador {
	@Autowired
	private Environment env;

	@Value("${server.port}")
	private Integer port;
	@Autowired
	private IproductoServicio iproductoServicio;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return iproductoServicio.findAll().stream().map(producto ->{
			//se utiliza beans environment
			//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/listar/{id}")
	public Producto detalle(@PathVariable Long id){
		Producto producto = iproductoServicio.findById(id);
		//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		producto.setPort(port);
		return producto;
	}
}
