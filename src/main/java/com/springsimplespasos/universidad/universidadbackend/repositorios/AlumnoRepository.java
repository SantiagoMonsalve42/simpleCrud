package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioAlumnos")
public interface AlumnoRepository extends PersonaRepository{
    @Query("select a from Alumno a where a.carrera.nombre = ?1")
    Iterable<Persona> buscarPorNombreCarrera(String name);

}
