package com.laurasoto.springboot.app.item.springbootservicioitem.controladores;

import com.laurasoto.springboot.app.commons.modelos.Producto;
import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.Item;
import com.laurasoto.springboot.app.item.springbootservicioitem.modelos.servicio.ItemServicio;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
@RefreshScope
@RestController
public class ItemControlador {
    private final Logger logger = LoggerFactory.getLogger(ItemControlador.class);
    @Autowired
    private Environment env;
    @Autowired
    private CircuitBreakerFactory cbFactory;
    @Autowired
    @Qualifier("serviceFeign")
    private ItemServicio itemServicio;

    @Value("${configuracion.texto}")
    private String texto;
    /*@Autowired
    @Qualifier("servicioRestTemplate")
    private ItemServicio itemServicio;*/
    @GetMapping("/listar")
    public List<Item> listar(@RequestParam (name = "nombre", required= false) String nombre,
                             @RequestHeader(name = "token-request", required= false) String token){
        System.out.println(nombre);
        System.out.println(token);
        return itemServicio.findAll();
    }

    @GetMapping("/listar/{id}/cantidad/{cantidad}")
    public Item item(@PathVariable Long id, @PathVariable Integer cantidad){
        return cbFactory.create("items")
            .run(() -> itemServicio.findById(id, cantidad) , e -> metodoAlternativo(id, cantidad, e));
    }
    @CircuitBreaker(name="items", fallbackMethod = "metodoAlternativo")
    @GetMapping("/listar2/{id}/cantidad/{cantidad}")
    public Item item2(@PathVariable Long id, @PathVariable Integer cantidad){
        return itemServicio.findById(id, cantidad);
    }
    @CircuitBreaker(name="items", fallbackMethod = "metodoAlternativo2")
    @TimeLimiter(name="items")
    @GetMapping("/listar3/{id}/cantidad/{cantidad}")
    public CompletableFuture<Item> item3(@PathVariable Long id, @PathVariable Integer cantidad){
        return CompletableFuture.supplyAsync(() -> itemServicio.findById(id, cantidad));
    }

    public Item metodoAlternativo(Long id, Integer cantidad, Throwable e){
        logger.info(e.getMessage());
        Item item = new Item();
        Producto producto = new Producto();

        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("sonycp");
        producto.setPrecio(400.00);
        item.setProducto(producto);
        return item;
    }

    public CompletableFuture<Item> metodoAlternativo2(Long id, Integer cantidad, Throwable e){
        logger.info(e.getMessage());
        Item item = new Item();
        Producto producto = new Producto();

        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("sonycp");
        producto.setPrecio(400.00);
        item.setProducto(producto);
        return CompletableFuture.supplyAsync(() -> item) ;
    }

    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){
        logger.info(texto);
        Map<String, String> json = new HashMap<>();
        json.put("puerto", puerto);
        json.put("texto", texto);
        if(env.getActiveProfiles().length >0 && env.getActiveProfiles()[0].equals("dev")){

            json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
            json.put("autor.email", env.getProperty("configuracion.autor.email"));
        }
        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto producto){
        return itemServicio.save(producto);
    }

    @PutMapping("editar/{id}")
    public Producto editar(@RequestBody Producto producto,
                           @PathVariable Long id){
        return itemServicio.update(producto, id);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
        public void eliminar(@PathVariable Long id){
            itemServicio.delete(id);
        }
}
