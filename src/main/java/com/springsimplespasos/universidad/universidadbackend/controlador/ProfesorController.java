package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/profesor")
public class ProfesorController extends PersonaController{
    public ProfesorController(@Qualifier("profesorDAOImp")PersonaDAO service) {
        super(service);
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
}
