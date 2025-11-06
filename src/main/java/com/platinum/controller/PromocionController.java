/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PromocionController {

    @GetMapping("/promociones")
    public String promociones(Model model) {
        // Aquí cargar una lista de promociones desde la BD mas adelante
        return "promociones"; 
    }
}
