package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.GenericDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GenericController <E,S extends GenericDAO<E>>{
    protected final S service;
    protected String nombreEntidad;

    public GenericController(S service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos(){
        Map<String,Object> mensaje = new HashMap<>();
        List<E> listado = (List<E>) service.findAll();
        if(listado.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje",String.format("No se han encontado %ss",nombreEntidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",listado);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> leerPorId(@PathVariable Integer id){
        Map<String,Object> mensaje = new HashMap<>();
        Optional<E> persona = service.findById(id);
        if(!persona.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontro "+id+" en la entidad "+nombreEntidad);

            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",persona);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody E e){
        Map<String,Object> mensaje = new HashMap<>();
        E save = service.save(e);
        E obj = null;
        if(save.equals(obj)){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","Error en el insert");

            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("data",save);
        return ResponseEntity.ok(mensaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        Map<String,Object> mensaje = new HashMap<>();
        Optional<E> byId = service.findById(id);
        if(!byId.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","Error al eliminar");
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

}
