/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.controller;

import com.platinum.domain.Promocion;
import com.platinum.service.PromocionService;
import com.platinum.service.FirebaseStorageService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @Autowired
    private MessageSource messageSource;

    // Vista pública de promociones (para clientes)
    @GetMapping("/promociones")
    public String promocionesPublicas(Model model) {
        var promociones = promocionService.getPromociones(true); // solo activas
        model.addAttribute("promociones", promociones);
        return "promociones";
    }

    // Administración: listado completo
    @GetMapping("/promocion/listado")
    public String listadoAdmin(Model model) {
        var promociones = promocionService.getPromociones(false); // todas
        model.addAttribute("promociones", promociones);
        model.addAttribute("totalPromociones", promociones.size());
        return "promocion/listado";
    }

    // Nuevo promoción (para admin)
    @GetMapping("/promocion/nuevo")
    public String promocionNueva(Model model) {
        model.addAttribute("promocion", new Promocion()); // objeto vacío
        return "promocion/modifica";
    }

    // Modificar (viene del botón editar)
    @PostMapping("/promocion/modificar")
    public String modificar(Promocion promocion, Model model) {
        promocion = promocionService.getPromocion(promocion);
        model.addAttribute("promocion", promocion);
        return "promocion/modifica";
    }

    // Guardar (nuevo o editar)
    @PostMapping("/promocion/guardar")
    public String guardar(Promocion promocion,
            @RequestParam MultipartFile imagenFile,
            RedirectAttributes redirectAttributes) {
        if (!imagenFile.isEmpty()) {
            promocionService.save(promocion); // guardar primero para tener ID
            String rutaImagen = firebaseStorageService
                    .cargaImagen(imagenFile, "promocion", promocion.getIdPromocion());
            promocion.setImagen(rutaImagen);
        }
        promocionService.save(promocion);

        redirectAttributes.addFlashAttribute("todoOk",
                messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/promocion/listado";
    }

    // Eliminar
    @PostMapping("/promocion/eliminar")
    public String eliminar(Promocion promocion, RedirectAttributes redirectAttributes) {
        promocion = promocionService.getPromocion(promocion);
        if (promocion == null) {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("promocion.error01", null, Locale.getDefault()));
        } else if (promocionService.delete(promocion)) {
            redirectAttributes.addFlashAttribute("todoOk",
                    messageSource.getMessage("mensaje.eliminado", null, Locale.getDefault()));
        } else {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("promocion.error03", null, Locale.getDefault()));
        }
        return "redirect:/promocion/listado";
    }
}
