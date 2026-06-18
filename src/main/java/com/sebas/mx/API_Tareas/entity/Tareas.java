package com.sebas.mx.API_Tareas.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.Instant;

@Entity
@Table(name = "Tareas")
@Data
public class Tareas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private Boolean completado;
    private Instant fechaCreacion;
}
