package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carreras")
public class CarreraController extends GenericController<Carrera,CarreraDAO>{

    @Autowired
    public CarreraController(CarreraDAO service) {
        super(service);
        nombreEntidad= "Carrera";
    }

    @PutMapping("/{id}")
    public Carrera update(@PathVariable Integer id,@RequestBody Carrera carrera){
        Carrera carreraUpdate=null;
        Optional<Carrera> byId = service.findById(id);
        if(!byId.isPresent()){
            throw new BadRequestException("No existe carrera con id "+id);
        }
        carreraUpdate = byId.get();
        if(carrera.getCantidadAnios() <0){
            throw new BadRequestException("La cantidad de años no puede ser negativa");
        }
        if(carrera.getCantidaMaterias() <0){
            throw new BadRequestException("La cantidad de materias no puede ser negativa");
        }
        carreraUpdate.setCantidadAnios(carrera.getCantidadAnios());
        carreraUpdate.setCantidaMaterias(carrera.getCantidaMaterias());
        return service.save(carreraUpdate);
    }

}
