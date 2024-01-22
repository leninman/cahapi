package com.ics.catpfanb.apirest.security;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
