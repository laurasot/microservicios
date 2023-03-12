package com.laurasoto.springboot.app.oauth.security;

import com.laurasoto.springboot.app.oauth.servicios.IUsuarioServicio;
import com.laurasoto.springboot.commons.usuarios.modelos.Usuario;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {
    private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
    @Autowired
    private IUsuarioServicio usuarioServicio;
    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        if (authentication.getName().equalsIgnoreCase("frontendapp")){
        //if(authentication.getDetails() instanceof WebAuthenticationDetails){
          return;
        }
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String mensaje = "Success Login: " + user.getUsername();
        System.out.println(mensaje);
        log.info(mensaje);
        Usuario usuario = usuarioServicio.findByUsername(authentication.getName());
        if (usuario.getIntentos() != null && usuario.getIntentos() > 0){
            usuario.setIntentos(0);
            usuarioServicio.update(usuario, usuario.getId());
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        String mensaje = "Error en el Login: " + exception.getMessage();
        log.error(mensaje);
        System.out.println(mensaje);

        try{
            Usuario usuario = usuarioServicio.findByUsername(authentication.getName());
            if(usuario.getIntentos() == null){
                usuario.setIntentos(0);
            }
            log.info("Intentos actual es de: " + usuario.getIntentos());
            usuario.setIntentos(usuario.getIntentos() + 1);
            log.info("Intentos actual es de: " + usuario.getIntentos());
            if (usuario.getIntentos()>=3){
                log.error(String.format("El usuario %s des-habilitado por maximos intentos.", usuario.getUsername()));
                usuario.setEnabled(false);
            }
            usuarioServicio.update(usuario, usuario.getId());
        } catch (FeignException e){
            log.error(String.format("el usuario no existe en el sistema", authentication.getName()));
        }

    }
}
