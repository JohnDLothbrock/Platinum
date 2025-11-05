/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.controller;

import com.platinum.service.GaleriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/galeria")
public class GaleriaController {

    @Autowired
    private GaleriaService galeriaService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var galerias = galeriaService.getGalerias(false);
        model.addAttribute("galerias", galerias);
        model.addAttribute("totalGalerias", galerias.size());
        return "galeria/listado";
    }
}
