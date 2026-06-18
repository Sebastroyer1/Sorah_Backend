package com.sebas.mx.API_Tareas.service;

import com.sebas.mx.API_Tareas.dto.TareaRequestDto;
import com.sebas.mx.API_Tareas.entity.Tareas;

import java.util.List;

public interface TareasService {

    Tareas crearTareas(TareaRequestDto tareas);
    List<Tareas> findAllTareas();
    Tareas findTareasById(Long id);
    Tareas actualizarTareas(TareaRequestDto tareas, Long id);
    void deleteTareasById(Long id);
}
