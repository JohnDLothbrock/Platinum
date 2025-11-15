
package com.platinum.controller;

import com.platinum.domain.Cita;
import com.platinum.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cita")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var citas = citaService.getCitas();
        model.addAttribute("citas", citas);
        model.addAttribute("totalCitas", citas.size());
        return "cita/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Cita cita) {
        citaService.save(cita);
        return "redirect:/cita/listado";
    }

    @GetMapping("/eliminar/{idCita}")
    public String eliminar(Cita cita) {
        citaService.delete(cita);
        return "redirect:/cita/listado";
    }

    @GetMapping("/modificar/{idCita}")
    public String modificar(Cita cita, Model model) {
        cita = citaService.getCita(cita);
        model.addAttribute("cita", cita);
        return "cita/modifica";
    }
}
