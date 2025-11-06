/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.controller;

import com.platinum.service.TestimonioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/testimonio")
public class TestimonioController {

    @Autowired
    private TestimonioService testimonioService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var testimonios = testimonioService.getTestimonios();
        model.addAttribute("testimonios", testimonios);
        model.addAttribute("totalTestimonios", testimonios.size());
        return "testimonio/listado";
    }
}
