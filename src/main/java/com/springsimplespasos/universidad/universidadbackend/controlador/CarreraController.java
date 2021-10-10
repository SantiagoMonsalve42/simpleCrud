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
    @GetMapping("/nombre-contains")
    public List<Carrera> findCarrerasByNombreContains(@RequestParam String query){
        List<Carrera> carrerasByNombreContains = (List<Carrera>) service.findCarrerasByNombreContains(query);
        if(carrerasByNombreContains.isEmpty()){
            throw new BadRequestException("Ninguna carrera contiene "+query);
        }
        return carrerasByNombreContains;
    }
    @GetMapping("/nombre-contains-ignore")
    public List<Carrera> findCarrerasByNombreContainsIgnoreCase(@RequestParam String query){
        List<Carrera> carrerasByNombreContains = (List<Carrera>) service.findCarrerasByNombreContainsIgnoreCase(query);
        if(carrerasByNombreContains.isEmpty()){
            throw new BadRequestException("Ninguna carrera contiene "+query);
        }
        return carrerasByNombreContains;
    }
    @GetMapping("/años")
    public List<Carrera> findCarrerasByCantidadAniosAfter (@RequestParam Integer query){
        List<Carrera> carrerasByNombreContains = (List<Carrera>) service.findCarrerasByCantidadAniosAfter(query);
        if(carrerasByNombreContains.isEmpty()){
            throw new BadRequestException("Ninguna carrera tiene mas años que "+query);
        }
        return carrerasByNombreContains;
    }
    @GetMapping("/docente")
    public List<Carrera> buscarCarrerasPorProfesorNombreYApellido(@RequestParam String name,@RequestParam String lastname){
        List<Carrera> carrerasByNombreContains = (List<Carrera>) service.buscarCarrerasPorProfesorNombreYApellido(name,lastname);
        if(carrerasByNombreContains.isEmpty()){
            throw new BadRequestException("Ninguna carrera pertenece al docente "+name+" "+lastname);
        }
        return carrerasByNombreContains;
    }
}
