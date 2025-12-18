/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.controller;

import com.platinum.domain.Membresia;
import com.platinum.service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/membresia")
public class MembresiaController {

    @Autowired
    private MembresiaService membresiaService;

    // PÚBLICO (tu menú apunta a /membresia)
    @GetMapping("")
    public String publico(Model model) {
        model.addAttribute("membresias", membresiaService.getMembresias());
        return "membresia/publico";
    }

    // ADMIN
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("membresias", membresiaService.getMembresias());
        return "membresia/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("membresia", new Membresia());
        return "membresia/modifica";
    }

    @GetMapping("/editar/{idMembresia}")
    public String editar(Membresia m, Model model) {
        m = membresiaService.getMembresia(m);
        model.addAttribute("membresia", m);
        return "membresia/modifica";
    }

    @PostMapping("/guardar")
    public String guardar(Membresia m) {
        membresiaService.save(m);
        return "redirect:/membresia/listado";
    }

    @GetMapping("/activar/{idMembresia}")
    public String activar(Membresia m) {
        membresiaService.activarDesactivar(m);
        return "redirect:/membresia/listado";
    }
}

