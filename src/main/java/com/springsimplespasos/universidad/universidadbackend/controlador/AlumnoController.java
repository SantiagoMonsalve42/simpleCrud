package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Alumno;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AlumnoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
@Api(value = "Acciones relacionadas con los alumnos", tags = "Alumnos")
public class AlumnoController extends PersonaController {
    private Map<String,Object> mensaje;
    private final CarreraDAO carreraDAO;

    @Autowired
    public AlumnoController(@Qualifier("alumnoDAOImp") PersonaDAO alumnoDAO, CarreraDAO carreraDAO) {
        super(alumnoDAO);
        nombreEntidad ="Alumno";
        this.carreraDAO = carreraDAO;
    }
    //sobrecarga de metodo para obtener solo los alumnos
    @Override
    public ResponseEntity<?> obtenerTodos(){
        mensaje = new HashMap<>();
        List<Persona> personas = (List<Persona>) service.readAllAlumnos();
        if(personas.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No hay alumnos");
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",personas);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/{id}")
    @ApiOperation(value="Editar por id")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody Persona alumno){
        mensaje = new HashMap<>();
        Persona alumnoUpdate = null;
        Optional<Persona> byId = service.findById(id);
        if(! byId.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontro alumno con id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        alumnoUpdate = byId.get();
        alumnoUpdate.setNombre(alumno.getNombre());
        alumnoUpdate.setApellido(alumno.getApellido());
        alumnoUpdate.setDireccion(alumno.getDireccion());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",service.save(alumnoUpdate));
        return ResponseEntity.ok(mensaje);
    }
    @ApiOperation(value="Asignar carrera a un alumno con sus ids")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    @PutMapping("/{idAlumno}/carrera/{idCarrera}")
    public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer idAlumno,@PathVariable Integer idCarrera){
        mensaje = new HashMap<>();
        Optional<Persona> byId = service.findById(idAlumno);
        Optional<Carrera> byIdCarrera = carreraDAO.findById(idCarrera);
        if(! byId.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontro alumno con id "+idAlumno);
            return ResponseEntity.badRequest().body(mensaje);
        }
        if(! byIdCarrera.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontro carrera con id "+idCarrera);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Persona alumno = byId.get();
        Carrera carrera = byIdCarrera.get();
        ((Alumno)alumno).setCarrera(carrera);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",service.save(alumno));
        return ResponseEntity.ok(mensaje);
    }
}
