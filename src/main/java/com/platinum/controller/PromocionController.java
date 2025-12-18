/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.controller;

import com.platinum.domain.Promocion;
import com.platinum.service.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    // PÚBLICO (tu menú apunta a /promociones)
    @GetMapping("/promociones")
    public String publico(Model model) {
        model.addAttribute("promociones", promocionService.getPromociones());
        return "promocion/publico";
    }

    // ADMIN
    @GetMapping("/promocion/listado")
    public String listado(Model model) {
        model.addAttribute("promociones", promocionService.getPromociones());
        return "promocion/listado";
    }

    @GetMapping("/promocion/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("promocion", new Promocion());
        return "promocion/modifica";
    }

    @GetMapping("/promocion/editar/{idPromocion}")
    public String editar(Promocion p, Model model) {
        p = promocionService.getPromocion(p);
        model.addAttribute("promocion", p);
        return "promocion/modifica";
    }

    @PostMapping("/promocion/guardar")
    public String guardar(Promocion p) {
        promocionService.save(p);
        return "redirect:/promocion/listado";
    }

    @GetMapping("/promocion/activar/{idPromocion}")
    public String activar(Promocion p) {
        promocionService.activarDesactivar(p);
        return "redirect:/promocion/listado";
    }
}

