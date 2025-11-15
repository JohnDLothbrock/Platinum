package com.platinum.service;

import com.platinum.domain.Cita;
import com.platinum.repository.CitaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Transactional(readOnly = true)
    public List<Cita> getCitas() {
        return citaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cita getCita(Cita cita) {
        return citaRepository.findById(cita.getIdCita()).orElse(null);
    }

    @Transactional
    public void save(Cita cita) {
        citaRepository.save(cita);
    }

    @Transactional
    public void delete(Cita cita) {
        citaRepository.delete(cita);
    }
}
