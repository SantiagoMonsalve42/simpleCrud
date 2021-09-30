package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PabellonRepository extends CrudRepository<Pabellon,Integer> {
    @Query("select p from Pabellon p where p.direccion.localidad = ?1")
    Iterable<Pabellon> buscarPorLocalidad(String localidad);
    @Query("select p from Pabellon p where p.nombre = ?1")
    Iterable<Pabellon> buscarPorNombre(String nombre);
}
