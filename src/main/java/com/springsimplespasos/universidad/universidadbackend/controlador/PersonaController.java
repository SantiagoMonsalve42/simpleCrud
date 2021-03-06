package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonaController extends GenericController<Persona, PersonaDAO> {
    private Map<String,Object> mensaje;
    public PersonaController(PersonaDAO service) {
        super(service);
    }

    @GetMapping("/nombre-apellido")
    @ApiOperation(value="Buscar por nombre y apellido")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> buscarPorNombreYApellido(@RequestParam String name, @RequestParam String lastname){
        mensaje = new HashMap<>();
        List<Persona> persona = (List<Persona>) service.buscarPorNombreYApellido(name, lastname);
        if(persona.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontro la persona "+name+" "+lastname);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",persona);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/dni")
    @ApiOperation(value="Buscar por numero de documento")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> buscarPorDNI(@RequestParam String dni){
        mensaje = new HashMap<>();
        Optional<Persona> persona = service.buscarPorDNI(dni);
        if(!persona.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontro la persona con dni "+dni);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",persona.get());
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/apellido")
    @ApiOperation(value="Buscar por persona")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> buscarPersonaPorApellido(@RequestParam String lastname){
        mensaje = new HashMap<>();
        List<Persona> personas = (List<Persona>) service.buscarPersonaPorApellido(lastname);
        personas.forEach(System.out::println);
        if(personas.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontro la persona con apellido "+lastname);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",personas);
        return ResponseEntity.ok(mensaje);
    }
}
