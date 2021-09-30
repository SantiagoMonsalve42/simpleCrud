package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import org.springframework.data.jpa.repository.Query;


public interface PabellonDAO extends GenericDAO<Pabellon> {
    Iterable<Pabellon> buscarPorLocalidad(String localidad);
    Iterable<Pabellon> buscarPorNombre(String nombre);
}
