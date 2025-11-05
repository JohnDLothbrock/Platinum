/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    public List<Servicio> getServicios(boolean activos) {
        var lista = servicioRepository.findAll();
        if (activos) {
            lista.removeIf(e -> !e.getActivo());
        }
        return lista;
    }
}