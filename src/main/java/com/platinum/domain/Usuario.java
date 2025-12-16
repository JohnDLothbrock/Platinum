/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.Data;

/**
 * @author anjuy
 */
@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(unique = true, nullable = false, length = 30)
    @NotBlank (message = "Usuario no puede estar vacio.")
    private String username;
    
    @Column(nullable = false, length = 512)
    @NotBlank (message = "La Contraseña No puede estar Vacia.")
    private String password;

    @Column(nullable = false, length = 50)
    @NotBlank (message = "No podemos proceder sin un Nombre.")
    private String nombre;

    @Column(length = 50)
    @NotBlank (message = "Los Apellidos No pueden estar Vacios.")
    private String apellidos;

    @Column(length = 75, unique = true)
    @NotBlank (message = "Necesitamos que ingreses un correo.")
    private String correo;

    @Column(length = 25)
    @NotBlank (message = "Por favor ingresar un numero de Telefono.")
    private String telefono;

    @Column(name = "ruta_imagen", length = 1024)
    private String rutaImagen;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private java.sql.Timestamp fechaCreacion;

    @Column(name = "fecha_modificacion", insertable = false, updatable = false)
    private java.sql.Timestamp fechaModificacion;


}
