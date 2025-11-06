/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestimonioController {

    @GetMapping("/testimonios")
    public String testimonios(Model model) {
        // Aqui agregar logica para cargar testimonios desde la BD
        return "testimonios"; 
    }
}
