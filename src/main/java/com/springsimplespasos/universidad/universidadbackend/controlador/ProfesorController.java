package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Profesor;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@RequestMapping("/profesor")
@Api(value = "Acciones relacionadas con los profesores", tags = "Profesores")
public class ProfesorController extends PersonaController{
    private Map<String,Object> mensaje;
    private CarreraDAO carreraDAO;

    public ProfesorController(@Qualifier("profesorDAOImp") PersonaDAO service, CarreraDAO carreraDAO) {
        super(service);
        this.carreraDAO = carreraDAO;
        nombreEntidad= "Profesor";
    }
    //SOBRE CARGA DE FUNCIONES USANDO HERENCIA
    @Override
    public ResponseEntity<?> obtenerTodos(){
        mensaje = new HashMap<>();
        List<Persona> personas = (List<Persona>) service.readAllProfesores();
        if(personas.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontraron profesores");
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",personas);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/{id}")
    @ApiOperation(value="Editar profesores por id")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> editar(@PathVariable Integer id, @RequestBody Persona alumno){
        mensaje = new HashMap<>();
        Persona profesorUpdate = null;
        Optional<Persona> byId = service.findById(id);
        if(! byId.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontro profesor con id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        profesorUpdate = byId.get();
        profesorUpdate.setNombre(alumno.getNombre());
        profesorUpdate.setApellido(alumno.getApellido());
        profesorUpdate.setDireccion(alumno.getDireccion());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",service.save(profesorUpdate));
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/carrera")
    @ApiOperation(value="Buscar profesores por carrera")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> buscarPorCarrera(@RequestParam String q){
        mensaje = new HashMap<>();
        List<Persona> profesoresByCarrera = (List<Persona>) ((ProfesorDAO) service).findProfesoresByCarrera(q);
        if(profesoresByCarrera.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No hay profesores asociados a la carrera "+q);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",profesoresByCarrera);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/{idDocente}/carrera/{idCarrera}")
    @ApiOperation(value="Asignar carrera a profesor por sus ids")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> asociarCarrera(@PathVariable Integer idDocente,@PathVariable Integer idCarrera){
        mensaje = new HashMap<>();
        Persona profesor = null;
        Carrera carrera = null;
        Optional<Persona> existeDocente = service.findById(idDocente);
        Optional<Carrera> existeCarrera = carreraDAO.findById(idCarrera);
        if(!existeDocente.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No existe docente con el id "+idDocente);
            return ResponseEntity.badRequest().body(mensaje);
        }
        if(!existeCarrera.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No existe carrera con el id "+idCarrera);
            return ResponseEntity.badRequest().body(mensaje);
        }
        profesor = existeDocente.get();
        carrera = existeCarrera.get();
        Set<Carrera> lista=((Profesor)profesor).getCarreras();
        lista.add(carrera);
        ((Profesor)profesor).setCarreras(lista);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",service.save(profesor));
        return ResponseEntity.ok(mensaje);

    }
}
