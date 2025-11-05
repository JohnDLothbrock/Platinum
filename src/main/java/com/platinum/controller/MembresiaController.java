/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.controller;

import com.platinum.service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/membresia")
public class MembresiaController {

    @Autowired
    private MembresiaService membresiaService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var membresias = membresiaService.getMembresias();
        model.addAttribute("membresias", membresias);
        model.addAttribute("totalMembresias", membresias.size());
        return "membresia/listado";
    }
}
