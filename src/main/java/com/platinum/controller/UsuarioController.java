/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.controller;

import com.platinum.domain.Usuario;
import com.platinum.repository.RolRepository;
import com.platinum.service.UsuarioService;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepository rolRepository;

    // lisatdo
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("usuarios", usuarioService.getUsuarios());
        return "usuario/listado";
    }

    // nuevo
    @GetMapping("/nuevo")
    public String nuevo(Usuario usuario, Model model) {
        model.addAttribute("roles", rolRepository.findAll());
        return "usuario/modifica";
    }

    // editar
    @GetMapping("/editar/{idUsuario}")
    public String editar(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolRepository.findAll());
        return "usuario/modifica";
    }

    // guardar
    @PostMapping("/guardar")
    public String guardar(
            Usuario usuario,
            @RequestParam(name = "rolesSeleccionados", required = false) Integer[] rolesIds,
            @RequestParam(name = "nuevaClave", defaultValue = "false") boolean nuevaClave
    ) {
        var roles = new HashSet<Integer>();
        if (rolesIds != null) {
            for (Integer id : rolesIds) {
                roles.add(id);
            }
        }
        usuarioService.save(usuario, roles, nuevaClave);
        return "redirect:/usuario/listado";
    }


    // activar / desactivar
    @GetMapping("/activar/{idUsuario}")
    public String activar(Usuario usuario) {
        usuarioService.activarDesactivar(usuario);
        return "redirect:/usuario/listado";
    }
}

