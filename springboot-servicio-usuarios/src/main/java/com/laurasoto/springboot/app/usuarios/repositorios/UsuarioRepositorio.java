package com.laurasoto.springboot.app.usuarios.repositorios;

import com.laurasoto.springboot.commons.usuarios.modelos.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "usuarios")
public interface UsuarioRepositorio extends PagingAndSortingRepository <Usuario, Long> {
    @RestResource(path = "buscar-username")
    public Usuario findByUsername(@Param("nombre") String username);
    @Query("select u from Usuario u where u.username=?1")
    public Usuario obtenerPorUsername(String username);
}
