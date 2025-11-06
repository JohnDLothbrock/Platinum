/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.service;

import com.platinum.domain.Constante;
import com.platinum.repository.ConstanteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConstanteService {

    @Autowired
    private ConstanteRepository constanteRepository;

    @Transactional(readOnly = true)
    public List<Constante> getConstantes() {
        return constanteRepository.findAll();
    }
}
