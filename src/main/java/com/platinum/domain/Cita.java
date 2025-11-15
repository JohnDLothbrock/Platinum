package com.platinum.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
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

    @Column(name = "nombre_cliente", nullable = false, length = 100)
    private String nombreCliente;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(length = 100)
    private String correo;

    @Column(name = "tipo_vehiculo", nullable = false, length = 30)
    private String tipoVehiculo;

    @Column(length = 10)
    private String placa;

    @Column(name = "servicio_solicitado", nullable = false, length = 100)
    private String servicioSolicitado;

    @Column(name = "fecha_cita", nullable = false)
    private Date fechaCita;

    @Column(name = "hora_cita", nullable = false)
    private Time horaCita;

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

