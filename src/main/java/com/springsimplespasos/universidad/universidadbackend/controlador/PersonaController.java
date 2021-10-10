package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

public class PersonaController extends GenericController<Persona, PersonaDAO> {

    public PersonaController(PersonaDAO service) {
        super(service);
    }

    @GetMapping("/nombre-apellido")
    public Persona buscarPorNombreYApellido(@RequestParam String name,@RequestParam String lastname){
        Optional<Persona> persona = service.buscarPorNombreYApellido(name, lastname);
        if(!persona.isPresent()){
            throw new BadRequestException("No se encontro la persona "+name+" "+lastname);
        }
        return persona.get();
    }

    @GetMapping("/dni")
    public Persona buscarPorDNI(@RequestParam String dni){
        Optional<Persona> persona = service.buscarPorDNI(dni);
        if(!persona.isPresent()){
            throw new BadRequestException("No se encontro la persona con dni "+dni);
        }
        return persona.get();
    }

    @GetMapping("/apellido")
    public List<Persona> buscarPersonaPorApellido(@RequestParam String lastname){
        List<Persona> personas = (List<Persona>) service.buscarPersonaPorApellido(lastname);
        personas.forEach(System.out::println);
        if(personas.isEmpty()){
            throw new BadRequestException("No se encontro la persona con apellido "+lastname);
        }
        return personas;
    }
}
