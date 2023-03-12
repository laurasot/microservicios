package com.laurasoto.springboot.app.oauth.clients;

import com.laurasoto.springboot.commons.usuarios.modelos.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "servicio-usuarios")
public interface UsuarioFeignClient {
    @GetMapping("/usuarios/search/buscar-username")
    public Usuario FindByUsername(@RequestParam("nombre") String username);

    @PutMapping("/usuarios/{id}")
    public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id);
}
