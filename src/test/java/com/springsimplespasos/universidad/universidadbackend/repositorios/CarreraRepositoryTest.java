package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.datos.DatosDummy;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarreraRepositoryTest {
    @Autowired
    CarreraRepository carreraRepository;

    @BeforeEach
    void setUp() {
        carreraRepository.save(DatosDummy.carrera01());
        carreraRepository.save(DatosDummy.carrera02());
        carreraRepository.save(DatosDummy.carrera03());
    }

    @AfterEach
    void tearDown() {
        carreraRepository.deleteAll();
    }

    @Test
    @DisplayName("buscar carreras por nombre")
    void findCarrerasByNombreContains() {
        //given
        //carreraRepository.save(DatosDummy.carrera01());
        //carreraRepository.save(DatosDummy.carrera02());
        //carreraRepository.save(DatosDummy.carrera03());
        //when
        Iterable<Carrera> expected = carreraRepository.findCarrerasByNombreContains("Sistemas");
        //then
        assertThat(((List<Carrera>)expected).size() ==1 ).isTrue();
    }

    @Test
    @DisplayName("buscar carreras por nombre sin importar mayusculas")
    void findCarrerasByNombreContainsIgnoreCase() {
        //given
        //carreraRepository.save(DatosDummy.carrera01());
        //carreraRepository.save(DatosDummy.carrera02());
        //carreraRepository.save(DatosDummy.carrera03());
        //when
        Iterable<Carrera> expected = carreraRepository.findCarrerasByNombreContainsIgnoreCase("sisTeMaS");
        //then
        assertThat(((List<Carrera>)expected).size() ==1 ).isTrue();
    }

    @Test
    @DisplayName("buscar carreras comparando años de duracion")
    void findCarrerasByCantidadAniosAfter() {
        //given
        //carreraRepository.save(DatosDummy.carrera01());
        //carreraRepository.save(DatosDummy.carrera02());
        //carreraRepository.save(DatosDummy.carrera03());
        //when
        Iterable<Carrera> expected = carreraRepository.findCarrerasByCantidadAniosAfter(2);
        //then
        assertThat(((List<Carrera>)expected).size() ==3 ).isTrue();
    }

    @Test
    @Disabled
    void buscarCarrerasPorProfesorNombreYApellido() {
    }
}