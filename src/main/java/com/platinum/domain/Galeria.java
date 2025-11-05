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
@Table(name = "galeria")
public class Galeria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_galeria")
    private Integer idGaleria;

    @Column(length = 100)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "tipo_servicio", length = 50)
    private String tipoServicio;

    @Column(name = "ruta_imagen_antes", length = 1024)
    private String rutaImagenAntes;

    @Column(name = "ruta_imagen_despues", length = 1024)
    private String rutaImagenDespues;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private java.sql.Timestamp fechaCreacion;
}
