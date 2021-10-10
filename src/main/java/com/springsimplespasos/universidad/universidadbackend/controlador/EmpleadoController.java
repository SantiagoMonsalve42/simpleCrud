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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController extends PersonaController{
    private PabellonDAO pabellonDAO;
    private Map<String,Object> mensaje;
    public EmpleadoController(@Qualifier("empleadoDAOImp") PersonaDAO service, PabellonDAO pabellonDAO) {
        super(service);
        this.pabellonDAO = pabellonDAO;
        nombreEntidad="Empleado";
    }
    //sobrecarga de metodo para obtener solo los alumnos
    @Override
    public ResponseEntity<?> obtenerTodos(){
        mensaje =new HashMap<>();
        List<Persona> personas = (List<Persona>) service.readAllEmpleados();
        if(personas.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No hay empleados");
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",personas);
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/tipo")
    public ResponseEntity<?> findEmpleadosByTipoEmpleado(@RequestParam TipoEmpleado tipo){
        mensaje =new HashMap<>();
        List<Persona> personas = (List<Persona>) ((EmpleadoDAO)service).findEmpleadoByTipoEmpleado(tipo);
        if(personas.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontraron empleados de tipo "+tipo);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",personas);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Integer id,@RequestBody Empleado empleado){
        mensaje =new HashMap<>();
        Persona empleadoUpdate = null;
        Optional<Persona> byId = service.findById(id);
        if(! byId.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontro empleado con id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        empleadoUpdate = byId.get();
        empleadoUpdate.setNombre(empleado.getNombre());
        empleadoUpdate.setApellido(empleado.getApellido());
        empleadoUpdate.setDireccion(empleado.getDireccion());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",service.save(empleadoUpdate));
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/{idEmpleado}/pabellon/{idPabellon}")
    public ResponseEntity<?> editarPabellon(@PathVariable Integer idEmpleado,@PathVariable Integer idPabellon){
        mensaje =new HashMap<>();
        Persona empleadoUpdate = null;
        Pabellon pabellonUpdate=null;
        Optional<Persona> empleado = service.findById(idEmpleado);
        Optional<Pabellon> pabellon = pabellonDAO.findById(idPabellon);
        if(!empleado.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontro el empleado con id "+idEmpleado);
            return ResponseEntity.badRequest().body(mensaje);
        }
        if(!pabellon.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No se encontro el pabellon con id "+idPabellon);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Persona persona = empleado.get();
        Pabellon pabellonExiste = pabellon.get();
        ((Empleado)persona).setPabellon(pabellonExiste);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",service.save(persona));
        return ResponseEntity.ok(mensaje);
    }
}
