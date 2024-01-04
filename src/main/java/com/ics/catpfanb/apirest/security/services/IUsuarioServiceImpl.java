package com.ics.catpfanb.apirest.security.services;



import com.ics.catpfanb.apirest.security.model.Usuario;
import com.ics.catpfanb.apirest.security.model.repo.UsuarioRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IUsuarioServiceImpl implements IUsuarioService {

    @Autowired
    UsuarioRepo usuarioRepo;

    private Usuario usuarioaguardado;

    @Override
    public Usuario salvarUsuario(Usuario usuario) {

        Usuario usuarioconsultado=usuarioRepo.findUsuarioByUsername(usuario.getUsername());

        if(usuarioconsultado==null) {
            usuario.setEnabled(true);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(encodedPassword);
            usuarioaguardado = usuarioRepo.save(usuario);
            return usuarioaguardado;
        }else{
            usuario.setEnabled(usuarioconsultado.getEnabled());
            return usuario;
        }


    }

    @Override
    public Usuario consultarUsuario(String nombreUsuario) {
        return usuarioRepo.findUsuarioByUsername(nombreUsuario);
    }

    @Override
    public void eliminarUsuario(Long idUsuario) {
        usuarioRepo.deleteById(idUsuario);
    }

    @Override
    public Usuario modificarusuario(Usuario usuario) {
        Usuario usuarioconsultado=usuarioRepo.findUsuarioByUsername(usuario.getUsername());

        if(usuarioconsultado!=null){
            usuario.setId(usuarioconsultado.getId());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(encodedPassword);
            usuario.setRoles(usuarioconsultado.getRoles());
            usuario.setEnabled(usuarioconsultado.getEnabled());
            usuarioaguardado=usuarioRepo.save(usuario);
            return usuarioaguardado;

        }
        else {
            return usuario;
        }
    }
}
