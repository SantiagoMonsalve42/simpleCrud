package com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.repositorios.AulaRepository;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AulaDAOImp extends GenericDAOImp<Aula, AulaRepository> implements AulaDAO {
    @Autowired
    public AulaDAOImp(AulaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Aula> buscarPorTipoPizarron(Pizarron tipoPizarron) {
        return repository.buscarPorTipoPizarron(tipoPizarron);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Aula> buscarPorNombrePabellon(String nombrePabellon) {
        return repository.buscarPorNombrePabellon(nombrePabellon);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Aula> buscarPorNumeroDeAula(Integer numeroAula) {
        return repository.buscarPorNumeroDeAula(numeroAula);
    }
}
