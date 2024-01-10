package com.ics.catpfanb.apirest.security.services;


import com.ics.catpfanb.apirest.security.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario salvarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();

    Usuario consultarUsuario(String nombreUsuario);

    void eliminarUsuario(Long idUsuario);

    Usuario modificarusuario(Usuario usuario);

}
