package com.laurasoto.springboot.app.item.springbootservicioitem.modelos.servicio;

import com.laurasoto.springboot.app.commons.modelos.Producto;
import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("servicioRestTemplate")
public class ItemServicioImpl implements ItemServicio{
    @Autowired
    private RestTemplate clienteRest;
    public List<Item> findAll(){
        List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://servicio-productos/listar", Producto[].class ));
        return productos.stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
    }
    public Item findById(Long id, Integer cantidad){
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        Producto producto = clienteRest.getForObject("http://servicio-productos/listar/{id}", Producto.class, pathVariables);
        return new Item(producto, cantidad);
    }

    @Override
    public Producto update(Producto producto,Long id) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
        ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/editar/{id}",
                HttpMethod.PUT, body, Producto.class, pathVariables);
        return response.getBody();
    }

    @Override
    public Producto save(Producto producto) {
        HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
        ResponseEntity<Producto> response = clienteRest.exchange("http://servicio-productos/crear", HttpMethod.POST,body, Producto.class);
        Producto productoResponse = response.getBody();
        return productoResponse;
    }

    @Override
    public void delete(Long id) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        clienteRest.delete("http://servicio-productos/eliminar/{id}", pathVariables);
    }
}
