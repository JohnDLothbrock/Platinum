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
@Table(name = "cita")
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Integer idCita;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_servicio", nullable = false)
    private Servicio servicio;

    @Column(name = "fecha_cita", nullable = false)
    private java.sql.Date fechaCita;

    @Column(name = "hora_cita", nullable = false)
    private java.sql.Time horaCita;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private Estado estado = Estado.Pendiente;

    @Column(columnDefinition = "TEXT")
    private String notas;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private java.sql.Timestamp fechaCreacion;

    @Column(name = "fecha_modificacion", insertable = false, updatable = false)
    private java.sql.Timestamp fechaModificacion;

    public enum Estado {
        Pendiente, Confirmada, Rechazada, Cancelada, Completada
    }
}
