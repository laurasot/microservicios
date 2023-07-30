package com.laurasoto.springBootServicioProductos.controladores;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.laurasoto.springBootServicioProductos.servicios.IproductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.laurasoto.springboot.app.commons.modelos.Producto;

@RestController
public class ProductoControlador {
	@Autowired
	private Environment env;

	@Value("${server.port}")
	private Integer port;

	private final IproductoServicio iproductoServicio;

	public ProductoControlador(IproductoServicio iproductoServicio){
		this.iproductoServicio = iproductoServicio;
	}
	
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
	public Producto detalle(@PathVariable Long id) throws InterruptedException {
		if(id.equals(10L)){
			throw new IllegalStateException("porducto no encontrado");
		}
		if(id.equals(7L)){
			TimeUnit.SECONDS.sleep(5L);
		}
		Producto producto = iproductoServicio.findById(id);
		//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		producto.setPort(port);
		return producto;
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto){
		return iproductoServicio.save(producto);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto,
						   @PathVariable Long id){
		Producto productoDb = iproductoServicio.findById(id);
		productoDb.setNombre(producto.getNombre());
		productoDb.setPrecio(producto.getPrecio());
		return iproductoServicio.save(productoDb);
	}

	@DeleteMapping("eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id){
	iproductoServicio.deleteById(id);
	}


}
