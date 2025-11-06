/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "cotizacion_imagen")
public class CotizacionImagen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Integer idImagen;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "ruta_imagen", length = 1024, nullable = false)
    private String rutaImagen;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private EstadoImagen estado = EstadoImagen.Pendiente;

    @Column(name = "fecha_subida", insertable = false, updatable = false)
    private java.sql.Timestamp fechaSubida;

    public enum EstadoImagen {
        Pendiente,
        Revisada
    }
}
