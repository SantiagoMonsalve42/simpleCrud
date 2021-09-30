package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class PersonaRepositoryTest {
    @Autowired
    @Qualifier("repositorioAlumnos")
    PersonaRepository alumnoRepository;
    @Autowired
    @Qualifier("EmpleadoRepository")
    PersonaRepository empleadoRepository;
    @Autowired
    @Qualifier("repositorioProfesores")
    PersonaRepository profesorRepository;

    @Test
    void buscarPorNombreYApellido() {
        //GIVEN
        Persona save = empleadoRepository.save(empleado01());
        //WHEN
        Optional<Persona> expected = empleadoRepository.buscarPorNombreYApellido(empleado01().getNombre(), empleado01().getApellido());
        //THEN
        assertThat(expected.get()).isInstanceOf(Empleado.class);
        assertThat(expected.get()).isEqualTo(save);
    }

    @Test
    void buscarPorDNI() {
        //Given
        Persona save = profesorRepository.save(profesor01());
        //when
        Optional<Persona> expected = profesorRepository.buscarPorDNI(profesor01().getDni());
        //then
        assertThat(expected.get()).isInstanceOf(Profesor.class);
        assertThat(expected.get()).isEqualTo(save);
        assertThat(expected.get().getDni()).isEqualTo(save.getDni());
    }

    @Test
    void buscarPersonaPorApellido() {
        //GIVEN
        Iterable<Persona> personas = alumnoRepository.saveAll(Arrays.asList(alumno01(), alumno02(), alumno03()));
        //WHEN
        String apellido= "RUIZ";
        List<Persona> expected = (List<Persona>) alumnoRepository.buscarPersonaPorApellido(apellido);
        //THEN
        assertThat(expected.size() == 2).isTrue();
    }
}