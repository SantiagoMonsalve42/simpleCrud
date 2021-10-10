package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AlumnoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController extends PersonaController {

    private final CarreraDAO carreraDAO;

    @Autowired
    public AlumnoController(@Qualifier("alumnoDAOImp") PersonaDAO alumnoDAO, CarreraDAO carreraDAO) {
        super(alumnoDAO);
        nombreEntidad ="Alumno";
        this.carreraDAO = carreraDAO;
    }
    //sobrecarga de metodo para obtener solo los alumnos
    @Override
    public List<Persona> obtenerTodos(){
        List<Persona> personas = (List<Persona>) service.readAllAlumnos();
        if(personas.isEmpty()){
            throw new BadRequestException("No hay alumnos");
        }
        return personas;
    }
    @PutMapping("/{id}")
    public Persona update(@PathVariable Integer id,@RequestBody Persona alumno){
        Persona alumnoUpdate = null;
        Optional<Persona> byId = service.findById(id);
        if(! byId.isPresent()){
            throw new BadRequestException("No se encontro alumno con id "+id);
        }
        alumnoUpdate = byId.get();
        alumnoUpdate.setNombre(alumno.getNombre());
        alumnoUpdate.setApellido(alumno.getApellido());
        alumnoUpdate.setDireccion(alumno.getDireccion());
        return service.save(alumnoUpdate);
    }

    @PutMapping("/{idAlumno}/carrera/{idCarrera}")
    public Persona asignarCarreraAlumno(@PathVariable Integer idAlumno,@PathVariable Integer idCarrera){
        Optional<Persona> byId = service.findById(idAlumno);
        Optional<Carrera> byIdCarrera = carreraDAO.findById(idCarrera);
        if(! byId.isPresent()){
            throw new BadRequestException("No se encontro alumno con id "+idAlumno);
        }
        if(! byIdCarrera.isPresent()){
            throw new BadRequestException("No se encontro carrera con id "+idCarrera);
        }
        Persona alumno = byId.get();
        Carrera carrera = byIdCarrera.get();
        ((Alumno)alumno).setCarrera(carrera);
        return service.save(alumno);
    }
}
