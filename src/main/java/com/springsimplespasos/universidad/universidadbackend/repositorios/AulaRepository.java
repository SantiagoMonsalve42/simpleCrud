package com.springsimplespasos.universidad.universidadbackend.repositorios;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AulaRepository extends CrudRepository<Aula,Integer> {
    @Query("select a from Aula a where a.pizarron = ?1")
    Iterable<Aula> buscarPorTipoPizarron(Pizarron tipoPizarron);
    @Query("select a from Aula a inner join a.pabellon p where p.nombre = ?1 ")
    Iterable<Aula> buscarPorNombrePabellon(String nombrePabellon);
    @Query("select a from Aula a where a.nroAula = ?1")
    Optional<Aula> buscarPorNumeroDeAula(Integer numeroAula);
}
