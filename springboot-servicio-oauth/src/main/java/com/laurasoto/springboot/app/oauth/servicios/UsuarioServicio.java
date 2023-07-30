package com.laurasoto.springboot.app.oauth.servicios;

import brave.Tracer;
import com.laurasoto.springboot.app.oauth.clients.UsuarioFeignClient;
import com.laurasoto.springboot.commons.usuarios.modelos.Usuario;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UsuarioServicio implements IUsuarioServicio , UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UsuarioServicio.class);

    private final UsuarioFeignClient client;
    @Autowired
    private Tracer tracer;
    public UsuarioServicio(UsuarioFeignClient client){
        this.client = client;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try{
            Usuario usuario = client.FindByUsername(username);
            List<GrantedAuthority> authorities = usuario.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                    .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                    .collect(Collectors.toList());
            logger.info("Usuario autenticado: " + username);
            return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(),
                    true, true, true, authorities);
        }
        catch(FeignException e){
            String error =  "Error en el login, no existe el usuario '"+username+"' en el sistema";
             logger.error(error);
             tracer.currentSpan().tag("error.mensaje", error + ": " + e.getMessage());
             throw new UsernameNotFoundException("Error en el login, no existe el usuario '\"+username+\"' en el sistema");
        }
    }

    @Override
    public Usuario findByUsername(String username) {
        return client.FindByUsername(username);
    }

    @Override
    public Usuario update(Usuario usuario, Long id) {
        return client.update(usuario, id);
    }
}
