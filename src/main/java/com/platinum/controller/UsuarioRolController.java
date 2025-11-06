/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.controller;

import com.platinum.service.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario-rol")
public class UsuarioRolController {

    @Autowired
    private UsuarioRolService usuarioRolService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var usuarioRoles = usuarioRolService.getUsuarioRoles();
        model.addAttribute("usuarioRoles", usuarioRoles);
        model.addAttribute("totalUsuarioRoles", usuarioRoles.size());
        return "usuario_rol/listado";
    }
}
