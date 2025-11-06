/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    public List<Promocion> getPromociones(boolean activas) {
        var lista = promocionRepository.findAll();
        if (activas) {
            lista.removeIf(e -> !e.getActiva());
        }
        return lista;
    }
}
