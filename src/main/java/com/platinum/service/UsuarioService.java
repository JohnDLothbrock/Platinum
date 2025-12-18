/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.service;

import com.platinum.domain.Rol;
import com.platinum.domain.Usuario;
import com.platinum.repository.RolRepository;
import com.platinum.repository.UsuarioRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        if (usuario == null || usuario.getIdUsuario() == null) {
            return null;
        }
        return usuarioRepository.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Transactional
    public void save(Usuario usuario, Set<Integer> rolesIds, boolean nuevaClave) {

        boolean esNuevo = (usuario.getIdUsuario() == null);

        if (esNuevo) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        } else {
            if (nuevaClave) {
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            } else {
                var existente = usuarioRepository.findById(usuario.getIdUsuario()).orElse(null);
                if (existente != null) {
                    usuario.setPassword(existente.getPassword());
                }
            }
        }

        if (usuario.getActivo() == null) {
            usuario.setActivo(true);
        }

        var setRoles = new HashSet<Rol>();
        if (rolesIds != null) {
            for (Integer idRol : rolesIds) {
                var rolOpt = rolRepository.findById(idRol);
                rolOpt.ifPresent(setRoles::add);
            }
        }
        usuario.setRoles(setRoles);

        usuarioRepository.save(usuario);
    }

    @Transactional
    public void activarDesactivar(Usuario usuario) {
        var obj = getUsuario(usuario);
        if (obj != null) {
            obj.setActivo(!obj.getActivo());
            usuarioRepository.save(obj);
        }
    }
}