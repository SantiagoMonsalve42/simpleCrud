package com.springsimplespasos.universidad.universidadbackend.servicios.implementaciones;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.repositorios.PabellonRepository;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PabellonDAOImp extends GenericDAOImp<Pabellon, PabellonRepository> implements PabellonDAO {
    @Autowired
    public PabellonDAOImp(PabellonRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pabellon> buscarPorLocalidad(String localidad) {
        return repository.buscarPorLocalidad(localidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pabellon> buscarPorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }
}
