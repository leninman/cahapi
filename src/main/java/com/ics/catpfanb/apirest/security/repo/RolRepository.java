package com.ics.catpfanb.apirest.security.repo;

import com.ics.catpfanb.apirest.security.entity.Rol;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends CrudRepository<Rol,Long> {
    Rol findByNombreRol(String nombreRol);


}
