package com.ics.catpfanb.apirest.security;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> usuarios();
    Optional<Usuario> consultarPorUsuario(String usuario);
    Usuario guardar(Usuario usuario);
}
