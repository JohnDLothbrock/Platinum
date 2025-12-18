package com.platinum.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "promocion")
public class Promocion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promocion")
    private Integer idPromocion;

    @Column(name = "titulo", nullable = false, length = 80)
    private String titulo;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "porcentaje_descuento", nullable = false)
    private Integer porcentajeDescuento = 0;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "imagen", length = 255)
    private String imagen; // ruta o URL

    @Column(name = "activa", nullable = false)
    private Boolean activa = true;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private Timestamp fechaCreacion;

    @Column(name = "fecha_modificacion", insertable = false, updatable = false)
    private Timestamp fechaModificacion;
}
