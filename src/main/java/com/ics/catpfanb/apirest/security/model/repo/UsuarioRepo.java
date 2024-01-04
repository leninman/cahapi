package com.ics.catpfanb.apirest.security.model.repo;


import com.ics.catpfanb.apirest.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepo extends JpaRepository<Usuario,Long> {

    Usuario findUsuarioByUsername(String username);


}
