

package com.platinum.controller;

import com.platinum.domain.Cita;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AgendarCitaController {

    @GetMapping("/agendar_cita")
    public String agendarCita(Cita cita) {
        return "agendar_cita";
    }
}

