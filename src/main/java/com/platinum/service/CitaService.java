/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
}
