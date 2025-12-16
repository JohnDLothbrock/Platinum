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
@RequestMapping("/promocion")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;   // CRUD

    @Autowired
    private FirebaseStorageService firebaseStorageService;   // Guardar Imágenes

    @Autowired
    private MessageSource messageSource;   // Mensajes personalizados

    // Listado de promociones
    @GetMapping("/listado")   // localhost:8080/promocion/listado
    public String inicio(Model model) {
        var promociones = promocionService.getPromociones(false);   // todas
        model.addAttribute("promociones", promociones);
        model.addAttribute("totalPromociones", promociones.size());
        return "/promocion/listado";   // tu vista listado.html
    }

    // Modificar (viene de botón editar)
    @PostMapping("/modificar")
    public String modificar(Promocion promocion, Model model) {
        promocion = promocionService.getPromocion(promocion);
        model.addAttribute("promocion", promocion);
        return "/promocion/modifica";   // tu vista modifica.html
    }

    // Guardar (nuevo o modificar)
    @PostMapping("/guardar")
    public String guardar(Promocion promocion,
            @RequestParam MultipartFile imagenFile,
            RedirectAttributes redirectAttributes) {

        if (!imagenFile.isEmpty()) {   // Si subieron imagen...
            promocionService.save(promocion);   // guardo primero para tener id
            String rutaImagen = firebaseStorageService
                    .cargaImagen(
                            imagenFile,
                            "promocion",
                            promocion.getIdPromocion());
            promocion.setImagen(rutaImagen);
        }
        promocionService.save(promocion);

        redirectAttributes.addFlashAttribute("todoOk",
                messageSource.getMessage("mensaje.actualizado",
                        null,
                        Locale.getDefault()));
        return "redirect:/promocion/listado";
    }

    // Eliminar
    @PostMapping("/eliminar")
    public String eliminar(Promocion promocion, RedirectAttributes redirectAttributes) {
        promocion = promocionService.getPromocion(promocion);

        if (promocion == null) {   // No existe
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("promocion.error01",
                            null,
                            Locale.getDefault()));
        } else if (false) {   // aquí pondrás la regla futura (ej: si tiene citas asociadas)
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("promocion.error02",
                            null,
                            Locale.getDefault()));
        } else if (promocionService.delete(promocion)) {
            // Se borró
            redirectAttributes.addFlashAttribute("todoOk",
                    messageSource.getMessage("mensaje.eliminado",
                            null,
                            Locale.getDefault()));
        } else {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("promocion.error03",
                            null,
                            Locale.getDefault()));
        }
        return "redirect:/promocion/listado";
    }

    // Nuevo (viene de botón “Agregar nueva promoción”)
    @GetMapping("/nuevo")
    public String promocionNueva(Promocion promocion, Model model) {
        model.addAttribute("promocion", promocion);   // objeto vacío
        return "/promocion/modifica";
    }
}
