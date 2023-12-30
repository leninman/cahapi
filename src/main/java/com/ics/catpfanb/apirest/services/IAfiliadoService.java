package com.ics.catpfanb.apirest.services;

import com.ics.catpfanb.apirest.models.entity.Afiliado;

import java.util.List;
import java.util.Optional;

public interface IAfiliadoService {
    List<Afiliado> findAll();
    Afiliado save(Afiliado afiliado);
    Optional<Afiliado> findById(Long id);
    void delete(Long id);
}
