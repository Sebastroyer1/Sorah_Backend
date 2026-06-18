package com.sebas.mx.API_Tareas.repository;

import com.sebas.mx.API_Tareas.entity.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TareasRepository extends JpaRepository<Tareas,Long> {
    Optional<Tareas> findTareasById(Long id);
}
