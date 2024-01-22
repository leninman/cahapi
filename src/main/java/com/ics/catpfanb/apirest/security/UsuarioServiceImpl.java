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
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> usuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> consultarPorUsuario(String usuario) {
        return usuarioRepository.findByNombreUsuario(usuario);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        List<Rol> roles=usuario.getRoles();
        List<Rol> rolesAsignados=new ArrayList<>();
        for(Rol rol:roles){
            if(rol.getNombreRol().equals("ROLE_ADMIN")){
               rolesAsignados.add(rolRepository.findByNombreRol("ROLE_USER"));
            }
            rolesAsignados.add(rolRepository.findByNombreRol(rol.getNombreRol()));
        }

        usuario.setRoles(rolesAsignados);

        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        return usuarioRepository.save(usuario);
    }
}
