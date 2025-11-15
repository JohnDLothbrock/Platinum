package com.platinum.service;

import com.platinum.domain.Servicio;
import com.platinum.repository.ServicioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Transactional(readOnly = true)
    public List<Servicio> getServicios(boolean soloActivos) {
        var lista = servicioRepository.findAll();
        if (soloActivos) {
            lista.removeIf(s -> !Boolean.TRUE.equals(s.getActivo()));
        }
        return lista;
    }

    @Transactional(readOnly = true)
    public Servicio getServicio(Servicio servicio) {
        return servicioRepository.findById(servicio.getIdServicio()).orElse(null);
    }

    @Transactional
    public void save(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    @Transactional
    public void delete(Servicio servicio) {
        servicioRepository.delete(servicio);
    }
}
