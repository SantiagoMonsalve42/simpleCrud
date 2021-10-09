package com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.repositorios.PersonaRepository;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class PersonaDAOImp extends GenericDAOImp<Persona, PersonaRepository> implements PersonaDAO {
    public PersonaDAOImp(PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> buscarPorNombreYApellido(String name, String lastName) {
        return repository.buscarPorNombreYApellido(name,lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> buscarPorDNI(String dni) {
        return repository.buscarPorDNI(dni);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarPersonaPorApellido(String apellido) {
        return repository.buscarPersonaPorApellido(apellido);
    }

    @Override
    public Iterable<Persona> readAllAlumnos() {
        return repository.readAllAlumnos();
    }

}
