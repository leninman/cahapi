package com.ics.catpfanb.apirest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{


    private final UsuarioRepository usuarioRepository;

    private RolRepository rolRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> usuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> consultarPorUsuario(String usuario) {
        return Optional.empty();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        return usuarioRepository.save(usuario);
    }
}
