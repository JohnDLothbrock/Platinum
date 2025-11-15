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
    public Membresia getMembresia(Membresia membresia) {
        return membresiaRepository.findById(membresia.getIdMembresia()).orElse(null);
    }

    @Transactional
    public void save(Membresia membresia) {
        membresiaRepository.save(membresia);
    }

    @Transactional
    public void delete(Membresia membresia) {
        membresiaRepository.delete(membresia);
    }
}
