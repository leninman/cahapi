package com.ics.catpfanb.apirest.security.service;

import com.ics.catpfanb.apirest.security.entity.Usuario;
import com.ics.catpfanb.apirest.security.repo.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional=usuarioRepository.findByNombreUsuario(username);
        if(usuarioOptional.isEmpty()){
            throw new UsernameNotFoundException("El usuario con nombre %s no existe en el sistema!");

        }
        Usuario usuario=usuarioOptional.orElseThrow();

        List<GrantedAuthority> authorities=usuario.getRoles().stream().map(rol->
                new SimpleGrantedAuthority(rol.getNombreRol())).collect(Collectors.toList());


        return new User(usuario.getNombreUsuario(),
                usuario.getClave(),
                usuario.getEnabled(),
                true,
                true,
                true,
                authorities);
    }
}
