package com.sebas.mx.API_Tareas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TareaRequestDto {

    private String titulo;
    private String descripcion;
    private Boolean completado;
}
