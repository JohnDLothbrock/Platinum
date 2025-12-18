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

    // CRUD - R = READ (lectura)
    @Transactional(readOnly = true)
    public List<Promocion> getPromociones() {
        return promocionRepository.findAll();
    }

    // Obtener una promoción por objeto (usado para editar o eliminar)
    @Transactional(readOnly = true)
    public Promocion getPromocion(Promocion p) {
        return promocionRepository.findById(p.getIdPromocion()).orElse(null);
    }

    // C = CREATE / U = UPDATE (guardar nuevo o modificar existente)
    @Transactional
    public void save(Promocion p) {
        if (p.getActiva() == null) p.setActiva(true);
        if (p.getPorcentajeDescuento() == null) p.setPorcentajeDescuento(0);
        promocionRepository.save(p);
    }

    // D = DELETE (eliminar promoción)
    @Transactional
    public void activarDesactivar(Promocion p) {
        var obj = getPromocion(p);
        if (obj != null) {
            obj.setActiva(!obj.getActiva());
            promocionRepository.save(obj);
        }
    }
}
