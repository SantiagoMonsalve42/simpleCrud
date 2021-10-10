package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PersonaRepository extends CrudRepository<Persona,Integer> {
    @Query("select p from Persona p where p.nombre = ?1 and p.apellido = ?2")
    Optional<Persona> buscarPorNombreYApellido(String name,String lastName);
    @Query("select p from Persona p where p.dni = ?1")
    Optional<Persona> buscarPorDNI(String dni);
    @Query("select p from Persona p where p.apellido like %?1%")
    Iterable<Persona> buscarPersonaPorApellido(String apellido);
    @Query("select a from Alumno a")
    Iterable<Persona> readAllAlumnos();
    @Query("select p from Profesor p")
    Iterable<Persona> readAllProfesores();
    /*@Query("select e from Empleados e")
    Iterable<Persona> readAllEmpleados();*/
}
