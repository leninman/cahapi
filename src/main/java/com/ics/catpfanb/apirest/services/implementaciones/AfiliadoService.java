package com.ics.catpfanb.apirest.services.implementaciones;

import com.ics.catpfanb.apirest.models.dao.IAfiliadoDao;
import com.ics.catpfanb.apirest.models.entity.Afiliado;
import com.ics.catpfanb.apirest.services.IAfiliadoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AfiliadoService implements IAfiliadoService {

    private final IAfiliadoDao afiliadoDao;

    public AfiliadoService(IAfiliadoDao afiliadoDao) {
        this.afiliadoDao = afiliadoDao;
    }

    @Override
    @Transactional
    public List<Afiliado> findAll() {
        return (List<Afiliado>) afiliadoDao.findAll();
    }

    @Override
    @Transactional
    public Afiliado save(Afiliado afiliado) {
        if(afiliado.getId()!=null) {
            Optional<Afiliado> optionalAfiliado = afiliadoDao.findById(afiliado.getId());
            optionalAfiliado.ifPresent(value -> afiliado.setCreateAt(value.getCreateAt()));
        }
        return afiliadoDao.save(afiliado);
    }

    @Override
    @Transactional
    public Optional<Afiliado> findById(Long id) {
        return afiliadoDao.findById(id);
    }

    @Override
    public void delete(Long id) {
        afiliadoDao.deleteById(id);
    }
}
