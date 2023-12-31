package com.ics.catpfanb.apirest.models.dao;


import com.ics.catpfanb.apirest.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserDao extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByUserName(String username);

}
