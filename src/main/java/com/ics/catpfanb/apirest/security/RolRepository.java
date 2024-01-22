package com.ics.catpfanb.apirest.security;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolRepository extends CrudRepository<Rol,Long> {
    Rol findByNombreRol(String nombreRol);


}
