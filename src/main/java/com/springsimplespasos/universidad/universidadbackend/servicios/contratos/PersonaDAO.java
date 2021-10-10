package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonaDAO extends GenericDAO<Persona>{
    Optional<Persona> buscarPorNombreYApellido(String name,String lastName);
    Optional<Persona> buscarPorDNI(String dni);
    Iterable<Persona> buscarPersonaPorApellido(String apellido);
    Iterable<Persona> readAllAlumnos();
    Iterable<Persona> readAllProfesores();
    Iterable<Persona> readAllEmpleados();
}
