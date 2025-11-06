/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.controller;

import com.platinum.service.ConstanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/constante")
public class ConstanteController {

    @Autowired
    private ConstanteService constanteService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var constantes = constanteService.getConstantes();
        model.addAttribute("constantes", constantes);
        model.addAttribute("totalConstantes", constantes.size());
        return "constante/listado";
    }
}
