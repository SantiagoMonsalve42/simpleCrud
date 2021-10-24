package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pabellon")
@Api(value = "Acciones relacionadas con los pabellones", tags = "Pabellones")
public class PabellonController extends GenericController<Pabellon, PabellonDAO> {
    private Map<String,Object> mensaje;
    public PabellonController(PabellonDAO service) {
        super(service);
    }
    @GetMapping("/localidad")
    @ApiOperation(value="Buscar pabellones por localidad")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> buscarPabellonesPorLocalidad(@RequestParam String query){
        mensaje = new HashMap<>();
        List<Pabellon> pabellons =(List<Pabellon>)service.buscarPorLocalidad(query);
        if(pabellons.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No existen pabellones en la localidad "+query);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",pabellons);
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/nombre")
    @ApiOperation(value="Buscar pabellones por nombre")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> buscarPabellonesPorNombre(@RequestParam String query){
        mensaje = new HashMap<>();
        List<Pabellon> pabellons =(List<Pabellon>)service.buscarPorNombre(query);
        if(pabellons.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No existen pabellones en la localidad "+query);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",pabellons);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @ApiOperation(value="Editar pabellones por id")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> editar(@PathVariable Integer id,@RequestBody Pabellon pabellon){
        mensaje = new HashMap<>();
        Optional<Pabellon> pabellonBusqueda= service.findById(id);
        if(!pabellonBusqueda.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No existe pabellon con ID "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Pabellon pabellonEditable = pabellonBusqueda.get();
        pabellonEditable.setDireccion(pabellon.getDireccion());
        pabellonEditable.setNombre(pabellon.getNombre());
        pabellonEditable.setMts2(pabellon.getMts2());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",service.save(pabellonEditable));
        return ResponseEntity.ok(mensaje);
    }


}
