package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
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
    public ResponseEntity<?> buscarPorNombreYApellido(@RequestParam String name, @RequestParam String lastname){
        mensaje = new HashMap<>();
        Optional<Persona> persona = service.buscarPorNombreYApellido(name, lastname);
        if(!persona.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontro la persona "+name+" "+lastname);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",persona.get());
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/dni")
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
