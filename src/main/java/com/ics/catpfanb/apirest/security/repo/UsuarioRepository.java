package com.ics.catpfanb.apirest.security.repo;

import com.ics.catpfanb.apirest.security.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
