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
@Table(name = "membresia")
public class Membresia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_membresia")
    private Integer idMembresia;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Tipo tipo;

    @Column(name = "fecha_inicio", nullable = false)
    private java.sql.Date fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private java.sql.Date fechaFin;

    @Column(columnDefinition = "TEXT")
    private String beneficios;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Estado estado = Estado.Activa;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private java.sql.Timestamp fechaCreacion;

    @Column(name = "fecha_modificacion", insertable = false, updatable = false)
    private java.sql.Timestamp fechaModificacion;

    public enum Tipo {
        Mensual, Anual
    }

    public enum Estado {
        Activa, Vencida, Cancelada
    }
}
