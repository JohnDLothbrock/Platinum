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
    public List<Promocion> getPromociones(boolean soloActivas) {
        // Leer todas las promociones de la BD
        var lista = promocionRepository.findAll();

        // Si solo queremos las activas, filtramos las inactivas
        if (soloActivas) {
            lista.removeIf(p -> !Boolean.TRUE.equals(p.getActiva()));
        }
        return lista;
    }

    // Obtener una promoción por objeto (usado para editar o eliminar)
    @Transactional(readOnly = true)
    public Promocion getPromocion(Promocion promocion) {
        return promocionRepository.findById(promocion.getIdPromocion()).orElse(null);
    }

    // C = CREATE / U = UPDATE (guardar nuevo o modificar existente)
    @Transactional
    public void save(Promocion promocion) {
        promocionRepository.save(promocion);
    }

    // D = DELETE (eliminar promoción)
    @Transactional
    public boolean delete(Promocion promocion) {
        try {
            promocionRepository.delete(promocion);
            promocionRepository.flush(); // Fuerza la sincronización con la BD
            return true;
        } catch (Exception e) {
            return false; // En caso de error (por ejemplo, promoción relacionada)
        }
    }
}
