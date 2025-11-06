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
@Table(name = "contacto")
public class Contacto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contacto")
    private Integer idContacto;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 25)
    private String telefono;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Canal canal = Canal.WhatsApp;

    @Column(name = "fecha_contacto", insertable = false, updatable = false)
    private java.sql.Timestamp fechaContacto;

    public enum Canal {
        WhatsApp, Correo
    }
}
