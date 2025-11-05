/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.service;

import com.platinum.domain.Galeria;
import com.platinum.repository.GaleriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GaleriaService {

    @Autowired
    private GaleriaRepository galeriaRepository;

    @Transactional(readOnly = true)
    public List<Galeria> getGalerias(boolean activos) {
        var lista = galeriaRepository.findAll();
        if (activos) {
            lista.removeIf(e -> !e.getActivo());
        }
        return lista;
    }
}
