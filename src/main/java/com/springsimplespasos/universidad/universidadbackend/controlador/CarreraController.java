package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Carrera carrera){
        Map<String,Object> mensaje = new HashMap<>();
        Carrera carreraUpdate=null;
        Optional<Carrera> byId = service.findById(id);
        if(!byId.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No existe la carrera con id "+id);

            return ResponseEntity.badRequest().body(mensaje);
        }
        carreraUpdate = byId.get();
        if(carrera.getCantidadAnios() <0){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","La cantidad de años no puede ser negativa");

            return ResponseEntity.badRequest().body(mensaje);
        }
        if(carrera.getCantidaMaterias() <0){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","La cantidad de materias no puede ser negativa");

            return ResponseEntity.badRequest().body(mensaje);
        }
        carreraUpdate.setCantidadAnios(carrera.getCantidadAnios());
        carreraUpdate.setCantidaMaterias(carrera.getCantidaMaterias());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",service.save(carreraUpdate));
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/nombre-contains")
    public ResponseEntity<?> findCarrerasByNombreContains(@RequestParam String query){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carrerasByNombreContains = (List<Carrera>) service.findCarrerasByNombreContains(query);
        if(carrerasByNombreContains.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","Ninguna carrera contiene "+query);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",carrerasByNombreContains);
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/nombre-contains-ignore")
    public ResponseEntity<?> findCarrerasByNombreContainsIgnoreCase(@RequestParam String query){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carrerasByNombreContains = (List<Carrera>) service.findCarrerasByNombreContainsIgnoreCase(query);
        if(carrerasByNombreContains.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","Ninguna carrera contiene "+query);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",carrerasByNombreContains);
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/años")
    public ResponseEntity<?> findCarrerasByCantidadAniosAfter (@RequestParam Integer query){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carrerasByNombreContains = (List<Carrera>) service.findCarrerasByCantidadAniosAfter(query);
        if(carrerasByNombreContains.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","Ninguna carrera tiene mas años que "+query);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",carrerasByNombreContains);
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/docente")
    public ResponseEntity<?> buscarCarrerasPorProfesorNombreYApellido(@RequestParam String name,@RequestParam String lastname){
        Map<String,Object> mensaje = new HashMap<>();
        List<Carrera> carrerasByNombreContains = (List<Carrera>) service.buscarCarrerasPorProfesorNombreYApellido(name,lastname);
        if(carrerasByNombreContains.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","Ninguna carrera pertenece al docente "+name+" "+lastname);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",carrerasByNombreContains);
        return ResponseEntity.ok(mensaje);
    }
}
