package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController()
@RequestMapping("/profesor")
public class ProfesorController extends PersonaController{
    private CarreraDAO carreraDAO;

    public ProfesorController(@Qualifier("profesorDAOImp") PersonaDAO service, CarreraDAO carreraDAO) {
        super(service);
        this.carreraDAO = carreraDAO;
        nombreEntidad= "Profesor";
    }
    @Override
    public List<Persona> obtenerTodos(){
        List<Persona> personas = (List<Persona>) service.readAllProfesores();
        if(personas.isEmpty()){
            throw new BadRequestException("No se encontraron profesores");
        }
        return personas;
    }
    @PutMapping("/{id}")
    public Persona editar(@PathVariable Integer id, @RequestBody Persona alumno){
        Persona profesorUpdate = null;
        Optional<Persona> byId = service.findById(id);
        if(! byId.isPresent()){
            throw new BadRequestException("No se encontro alumno con id "+id);
        }
        profesorUpdate = byId.get();
        profesorUpdate.setNombre(alumno.getNombre());
        profesorUpdate.setApellido(alumno.getApellido());
        profesorUpdate.setDireccion(alumno.getDireccion());
        return service.save(profesorUpdate);
    }
    @GetMapping("/carrera")
    public List<Persona> buscarPorCarrera(@RequestParam String q){
        List<Persona> profesoresByCarrera = (List<Persona>) ((ProfesorDAO) service).findProfesoresByCarrera(q);
        if(profesoresByCarrera.isEmpty()){
            throw new BadRequestException("No hay profesores asociados a la carrera "+q);
        }
        return profesoresByCarrera;
    }
    @PutMapping("/{idDocente}/carrera/{idCarrera}")
    public Persona asociarCarrera(@PathVariable Integer idDocente,@PathVariable Integer idCarrera){
        Persona profesor = null;
        Carrera carrera = null;
        Optional<Persona> existeDocente = service.findById(idDocente);
        Optional<Carrera> existeCarrera = carreraDAO.findById(idCarrera);
        if(!existeDocente.isPresent()){
            throw new BadRequestException("No existe docente con el id "+idDocente);
        }
        if(!existeCarrera.isPresent()){
            throw new BadRequestException("No existe carrera con el id "+idCarrera);
        }
        profesor = existeDocente.get();
        carrera = existeCarrera.get();
        Set<Carrera> lista=((Profesor)profesor).getCarreras();
        lista.add(carrera);
        ((Profesor)profesor).setCarreras(lista);
        return service.save(profesor);

    }
}
