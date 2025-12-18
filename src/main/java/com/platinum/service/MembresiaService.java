package com.platinum.service;

import com.platinum.domain.Membresia;
import com.platinum.repository.MembresiaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembresiaService {

    @Autowired
    private MembresiaRepository membresiaRepository;

    @Transactional(readOnly = true)
    public List<Membresia> getMembresias() {
        return membresiaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Membresia getMembresia(Membresia m) {
        return membresiaRepository.findById(m.getIdMembresia()).orElse(null);
    }

    @Transactional
    public void save(Membresia m) {
        if (m.getActivo() == null) m.setActivo(true);
        if (m.getEstado() == null || m.getEstado().isBlank()) m.setEstado("ACTIVA");
        membresiaRepository.save(m);
    }

    @Transactional
    public void activarDesactivar(Membresia m) {
        var obj = getMembresia(m);
        if (obj != null) {
            obj.setActivo(!obj.getActivo());
            membresiaRepository.save(obj);
        }
    }
}
