package com.ics.catpfanb.apirest.security.services;


import com.ics.catpfanb.apirest.security.model.Usuario;

public interface IUsuarioService {
    Usuario salvarUsuario(Usuario usuario);

    Usuario consultarUsuario(String nombreUsuario);

    void eliminarUsuario(Long idUsuario);

    Usuario modificarusuario(Usuario usuario);

}
