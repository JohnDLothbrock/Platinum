/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.controller;

import com.platinum.service.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/promocion")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var promociones = promocionService.getPromociones(false);
        model.addAttribute("promociones", promociones);
        model.addAttribute("totalPromociones", promociones.size());
        return "promocion/listado";
    }
}
