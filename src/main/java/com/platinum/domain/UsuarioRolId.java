/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class UsuarioRolId implements Serializable {

    private Integer idUsuario;
    private Integer idRol;
}
