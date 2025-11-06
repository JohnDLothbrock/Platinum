/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

@Data
@Entity
@IdClass(UsuarioRolId.class)
@Table(name = "usuario_rol")
public class UsuarioRol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Id
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "fecha_asignacion", insertable = false, updatable = false)
    private Timestamp fechaAsignacion;
}
