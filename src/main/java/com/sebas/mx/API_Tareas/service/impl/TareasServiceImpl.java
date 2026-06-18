package com.sebas.mx.API_Tareas.service.impl;

import com.sebas.mx.API_Tareas.dto.TareaRequestDto;
import com.sebas.mx.API_Tareas.entity.Tareas;
import com.sebas.mx.API_Tareas.exception.BadRequestException;
import com.sebas.mx.API_Tareas.exception.TareaNotFoundException;
import com.sebas.mx.API_Tareas.repository.TareasRepository;
import com.sebas.mx.API_Tareas.service.TareasService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TareasServiceImpl implements TareasService {

    private final TareasRepository tareasRepository;

    @Override
    public Tareas crearTareas(TareaRequestDto tareas) {
        if (tareas == null) {
            throw new BadRequestException("La tarea no puede ser nula");
        }
        if (tareas.getTitulo() == null || tareas.getTitulo().isBlank()) {
            throw new BadRequestException("El título de la tarea es obligatorio");
        }

        Tareas tarea = new Tareas();
        tarea.setTitulo(tareas.getTitulo());
        tarea.setDescripcion(tareas.getDescripcion());
        tarea.setFechaCreacion(Instant.now());
        tarea.setCompletado(tareas.getCompletado() != null ? tareas.getCompletado() : false);

        return tareasRepository.save(tarea);
    }

    @Override
    public List<Tareas> findAllTareas() {
        List<Tareas> tareas = tareasRepository.findAll();
        return tareas != null ? tareas : Collections.emptyList();
    }

    @Override
    public Tareas findTareasById(Long id) {
        if (id == null) {
            throw new BadRequestException("Falta el id");
        }
        return tareasRepository.findTareasById(id)
                .orElseThrow(() -> new TareaNotFoundException("Tarea no encontrada: " + id));
    }


    @Transactional
    @Override
    public Tareas actualizarTareas(TareaRequestDto tareas, Long id) {
        if (id == null) {
            throw new BadRequestException("El id es nulo");
        }
        if (tareas == null) {
            throw new BadRequestException("Los datos de la tarea no pueden ser nulos");
        }

        Tareas tareaExistente = tareasRepository.findById(id)
                .orElseThrow(() -> new TareaNotFoundException("Tarea no encontrada: " + id));

        if (tareas.getTitulo() != null && !tareas.getTitulo().isBlank()) {tareaExistente.setTitulo(tareas.getTitulo());}
        if (tareas.getDescripcion() != null && !tareas.getDescripcion().isBlank()) {tareaExistente.setDescripcion(tareas.getDescripcion());}
        if (tareas.getCompletado() != null) {tareaExistente.setCompletado(tareas.getCompletado());}
        return tareasRepository.save(tareaExistente);
    }


    @Override
    public void deleteTareasById(Long id) {
        if (id == null) {
            throw new BadRequestException("Falta id");
        }
        Tareas eliminarTarea = tareasRepository.findById(id)
                .orElseThrow(() -> new TareaNotFoundException("Tarea no encontrada: " + id));
        tareasRepository.delete(eliminarTarea);
    }
}
