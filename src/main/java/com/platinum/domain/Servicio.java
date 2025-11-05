package com.platinum.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "servicio")
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer idServicio;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo", nullable = false, length = 20)
    private TipoVehiculo tipoVehiculo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Paquete paquete;

    @Column(nullable = false)
    private Double precio;

    @Column(name = "duracion_estimada")
    private Integer duracionEstimada;

    @Column(name = "ruta_imagen", length = 1024)
    private String rutaImagen;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private java.sql.Timestamp fechaCreacion;

    @Column(name = "fecha_modificacion", insertable = false, updatable = false)
    private java.sql.Timestamp fechaModificacion;

    public enum TipoVehiculo {
        Sedán, SUV, Pickup, Motocicleta
    }

    public enum Paquete {
        Básico, Premium, VIP
    }
}
