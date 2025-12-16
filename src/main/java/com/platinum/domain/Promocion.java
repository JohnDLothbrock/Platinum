package com.platinum.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Titulo no debe estar vacio.")
    private String titulo;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "La descripcion es necesaria para proceder.")
    private String descripcion;

    @Column(name = "porcentaje_descuento", precision = 5, scale = 2)
    private BigDecimal porcentajeDescuento;

    @NotNull(message = "La fecha de inicio es obligatoria.")
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria.")
    @Column(name = "fecha_fin", nullable = false)
    private Date fechaFin;

    @Column(length = 1024)
    private String imagen;

    @Column(nullable = false)
    private Boolean activa = true;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private java.sql.Timestamp fechaCreacion;

    @Column(name = "fecha_modificacion", insertable = false, updatable = false)
    private java.sql.Timestamp fechaModificacion;
}
