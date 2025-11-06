/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.service;

import com.platinum.domain.UsuarioRol;
import com.platinum.repository.UsuarioRolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioRolService {

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Transactional(readOnly = true)
    public List<UsuarioRol> getUsuarioRoles() {
        return usuarioRolRepository.findAll();
    }
}
