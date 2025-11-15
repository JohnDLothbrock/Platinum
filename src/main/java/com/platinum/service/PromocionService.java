
package com.platinum.service;

import com.platinum.domain.Promocion;
import com.platinum.repository.PromocionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PromocionService {

    @Autowired
    private PromocionRepository promocionRepository;

    @Transactional(readOnly = true)
    public List<Promocion> getPromociones(boolean soloActivas) {
        var lista = promocionRepository.findAll();
        if (soloActivas) {
            lista.removeIf(p -> !Boolean.TRUE.equals(p.getActiva()));
        }
        return lista;
    }

    @Transactional(readOnly = true)
    public Promocion getPromocion(Promocion promocion) {
        return promocionRepository.findById(promocion.getIdPromocion()).orElse(null);
    }

    @Transactional
    public void save(Promocion promocion) {
        promocionRepository.save(promocion);
    }

    @Transactional
    public void delete(Promocion promocion) {
        promocionRepository.delete(promocion);
    }
}
