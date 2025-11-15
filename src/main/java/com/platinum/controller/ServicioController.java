
package com.platinum.controller;

import com.platinum.domain.Servicio;
import com.platinum.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/nuevo")
    public String nuevo(Servicio servicio) {
        return "servicio/modifica";
    }

    @PostMapping("/guardar")
    public String guardar(Servicio servicio) {
        servicioService.save(servicio);
        return "redirect:/servicio/listado";
    }

    @GetMapping("/eliminar/{idServicio}")
    public String eliminar(Servicio servicio) {
        servicioService.delete(servicio);
        return "redirect:/servicio/listado";
    }

    @GetMapping("/modificar/{idServicio}")
    public String modificar(Servicio servicio, Model model) {
        servicio = servicioService.getServicio(servicio);
        model.addAttribute("servicio", servicio);
        return "servicio/modifica";
    }
}
