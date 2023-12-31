package com.ics.catpfanb.apirest.models.dao;


import com.ics.catpfanb.apirest.models.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolDao extends JpaRepository<Rol,Long> {
    Optional<Rol> findRolByRolName(String rolName);
}
