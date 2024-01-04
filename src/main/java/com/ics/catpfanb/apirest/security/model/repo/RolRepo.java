package com.ics.catpfanb.apirest.security.model.repo;


import com.ics.catpfanb.apirest.security.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepo extends JpaRepository<Rol,Long> {
}
