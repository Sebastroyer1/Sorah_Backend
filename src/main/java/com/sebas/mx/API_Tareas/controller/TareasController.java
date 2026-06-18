package com.sebas.mx.API_Tareas.controller;

import com.sebas.mx.API_Tareas.dto.TareaRequestDto;
import com.sebas.mx.API_Tareas.entity.Tareas;
import com.sebas.mx.API_Tareas.service.TareasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
public class TareasController {

    private final TareasService tareasService;

    @PostMapping("/nueva")
    public ResponseEntity<Tareas> crearTarea(@RequestBody TareaRequestDto request){
        Tareas response = tareasService.crearTareas(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tareas> actualizarTarea(@PathVariable Long id, @RequestBody TareaRequestDto request) {
        Tareas response = tareasService.actualizarTareas(request, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Tareas>> obtenerTodasLasTareas(){
        List<Tareas> response = tareasService.findAllTareas();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tareas> obtenerTareaPorId(@PathVariable Long id) {
        Tareas response = tareasService.findTareasById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        tareasService.deleteTareasById(id);
        return ResponseEntity.noContent().build();
    }
}
