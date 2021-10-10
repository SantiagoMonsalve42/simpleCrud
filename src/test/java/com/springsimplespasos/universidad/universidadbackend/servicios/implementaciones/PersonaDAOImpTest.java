package com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones;

import com.springsimplespasos.universidad.universidadbackend.repositorios.AlumnoRepository;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonaDAOImpTest {
    PersonaDAO personaDAO;
    @Mock
    AlumnoRepository alumnoRepository;

    @BeforeEach
    void setUp() {
        personaDAO=new PersonaDAOImp(alumnoRepository);
    }

    @Test
    void buscarPorNombreYApellido() {
        //when
        personaDAO.buscarPorNombreYApellido(anyString(),anyString());
        //then
        verify(alumnoRepository).buscarPorNombreYApellido(anyString(),anyString());
    }

    @Test
    void buscarPorDNI() {
        //when
        personaDAO.buscarPorDNI(anyString());
        //then
        verify(alumnoRepository).buscarPorDNI(anyString());
    }

    @Test
    void buscarPersonaPorApellido() {
        //when
        personaDAO.buscarPersonaPorApellido(anyString());
        //then
        verify(alumnoRepository).buscarPersonaPorApellido(anyString());
    }
}