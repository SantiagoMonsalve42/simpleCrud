package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.GenericDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class GenericController <E,S extends GenericDAO<E>>{
    protected final S service;
    protected String nombreEntidad;

    public GenericController(S service) {
        this.service = service;
    }

    @GetMapping
    public List<E> obtenerTodos(){
        List<E> listado = (List<E>) service.findAll();
        if(listado.isEmpty()){
            throw new BadRequestException(String.format("No se han encontado %ss",nombreEntidad));
        }
        return listado;
    }

    @GetMapping("/{id}")
    public E leerPorId(@PathVariable Integer id){
        Optional<E> persona = service.findById(id);
        if(!persona.isPresent()){
            throw new BadRequestException("No se encontro "+id+" en la entidad "+nombreEntidad);
        }
        return persona.get();
    }

    @PostMapping
    public E guardar(@RequestBody E e){
        return service.save(e);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id){
        Optional<E> byId = service.findById(id);
        if(!byId.isPresent()){
            throw new BadRequestException("Error al eliminar "+nombreEntidad+", verifique id");
        }
        service.deleteById(id);
    }

}
