/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.controller;


import com.platinum.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var servicios = servicioService.getServicios(false);
        model.addAttribute("servicios", servicios);
        model.addAttribute("totalServicios", servicios.size());
        return "servicio/listado";
    }
}