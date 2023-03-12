package com.laurasoto.springboot.app.oauth.servicios;

import com.laurasoto.springboot.commons.usuarios.modelos.Usuario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUsuarioServicio {
    public Usuario findByUsername(String username);
    public Usuario update(Usuario usuario,Long id);

}
