package com.ics.catpfanb.apirest.security.service;

import com.ics.catpfanb.apirest.security.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> usuarios();
    Optional<Usuario> consultarPorUsuario(String usuario);
    Optional<Usuario> findById(Long id);
    Usuario guardar(Usuario usuario);
    void delete(Long id);
}
