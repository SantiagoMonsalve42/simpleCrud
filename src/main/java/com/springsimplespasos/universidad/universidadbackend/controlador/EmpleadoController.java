package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Empleado;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.EmpleadoDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController extends PersonaController{
    private PabellonDAO pabellonDAO;
    public EmpleadoController(@Qualifier("empleadoDAOImp") PersonaDAO service, PabellonDAO pabellonDAO) {
        super(service);
        this.pabellonDAO = pabellonDAO;
        nombreEntidad="Empleado";
    }
    //sobrecarga de metodo para obtener solo los alumnos
    @Override
    public List<Persona> obtenerTodos(){
        List<Persona> personas = (List<Persona>) service.readAllEmpleados();
        if(personas.isEmpty()){
            throw new BadRequestException("No hay empleados");
        }
        return personas;
    }
    @GetMapping("/tipo")
    public List<Persona> findEmpleadosByTipoEmpleado(@RequestParam TipoEmpleado tipo){
        List<Persona> personas = (List<Persona>) ((EmpleadoDAO)service).findEmpleadoByTipoEmpleado(tipo);
        if(personas.isEmpty()){
            throw new BadRequestException("No se encontraron empleados de tipo "+tipo);
        }
        return personas;
    }
    @PutMapping("/{id}")
    public Persona editar(@PathVariable Integer id,@RequestBody Empleado empleado){
        Persona empleadoUpdate = null;
        Optional<Persona> byId = service.findById(id);
        if(! byId.isPresent()){
            throw new BadRequestException("No se encontro empleado con id "+id);
        }
        empleadoUpdate = byId.get();
        empleadoUpdate.setNombre(empleado.getNombre());
        empleadoUpdate.setApellido(empleado.getApellido());
        empleadoUpdate.setDireccion(empleado.getDireccion());
        return service.save(empleadoUpdate);
    }
    @PutMapping("/{idEmpleado}/pabellon/{idPabellon}")
    public Persona editarPabellon(@PathVariable Integer idEmpleado,@PathVariable Integer idPabellon){
        Persona empleadoUpdate = null;
        Pabellon pabellonUpdate=null;
        Optional<Persona> empleado = service.findById(idEmpleado);
        Optional<Pabellon> pabellon = pabellonDAO.findById(idPabellon);
        if(!empleado.isPresent()){
            throw new BadRequestException("No se encontro el empleado con id "+idEmpleado);
        }
        if(!pabellon.isPresent()){
            throw new BadRequestException("No se encontro el pabellon con id "+idPabellon);
        }
        Persona persona = empleado.get();
        Pabellon pabellonExiste = pabellon.get();
        ((Empleado)persona).setPabellon(pabellonExiste);
        return service.save(persona);
    }
}
