package com.springsimplespasos.universidad.universidadbackend.servicios.contratos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AulaDAO extends GenericDAO<Aula> {
    Iterable<Aula> buscarPorTipoPizarron(Pizarron tipoPizarron);
    Iterable<Aula> buscarPorNombrePabellon(String nombrePabellon);
    Optional<Aula> buscarPorNumeroDeAula(Integer numeroAula);
}
