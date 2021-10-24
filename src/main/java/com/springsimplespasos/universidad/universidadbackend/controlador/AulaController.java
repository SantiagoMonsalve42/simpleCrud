package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
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
@RequestMapping("/aula")
@Api(value = "Acciones relacionadas con las aulas", tags = "Aulas")
public class AulaController extends GenericController<Aula, AulaDAO> {
    private Map<String,Object> mensaje;
    private PabellonDAO pabellonDAO;
    public AulaController(AulaDAO service, PabellonDAO pabellonDAO) {
        super(service);
        this.pabellonDAO = pabellonDAO;
    }
    @GetMapping("/pizarron")
    @ApiOperation(value="Buscar aulas por tipo de pizarron")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> findAulasByPizarron(@RequestParam Pizarron query){
        mensaje = new HashMap<>();
        List<Aula> aulas = (List<Aula>) service.buscarPorTipoPizarron(query);
        if(aulas.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No hay aulas con pizarron del tipo "+query);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",aulas);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/pabellon-nombre")
    @ApiOperation(value="Buscar aulas por nombre de pabellon")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> findAulasByPabellonNombre (@RequestParam String query){
        mensaje = new HashMap<>();
        List<Aula> aulas = (List<Aula>) service.buscarPorNombrePabellon(query);
        if(aulas.isEmpty()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No hay aulas con pertencientes al pabellon "+query);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",aulas);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/numero-aula")
    @ApiOperation(value="Buscar aulas por numero de aula")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> findAulaByNroAula(@RequestParam Integer query){
        mensaje = new HashMap<>();
        Optional<Aula> aulas = service.buscarPorNumeroDeAula(query);
        if(!aulas.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No hay aulas con el numero "+query);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",aulas.get());
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @ApiOperation(value="Editar por id")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> editar (@PathVariable Integer id,@RequestBody Aula aula){
        mensaje = new HashMap<>();
        Optional<Aula> byId = service.findById(id);
        if(!byId.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No existe aula con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Aula aulaGuardar = byId.get();
        aulaGuardar.setCantidadPupitres(aula.getCantidadPupitres());
        aulaGuardar.setPizarron(aula.getPizarron());
        aulaGuardar.setMedidas(aula.getMedidas());
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",service.save(aulaGuardar));
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/{idAula}/pabellon/{idPabellon}")
    @ApiOperation(value="Asignar aula a pabellon con sus ids")
    @ApiResponses({
            @ApiResponse(code = 200,message = "ejecutado correctamente")
    })
    public ResponseEntity<?> asignarAPabellon(@PathVariable Integer idAula,@PathVariable Integer idPabellon){
        mensaje = new HashMap<>();
        Optional<Aula> aulaBusqueda = service.findById(idAula);
        Optional<Pabellon> pabellonBusqueda = pabellonDAO.findById(idPabellon);
        if(!aulaBusqueda.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No existe aula con id "+idAula);
            return ResponseEntity.badRequest().body(mensaje);
        }
        if(!pabellonBusqueda.isPresent()){
            mensaje.put("success",Boolean.FALSE);
            mensaje.put("mensaje","No existe el pabellon con id "+idPabellon);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Aula aula = aulaBusqueda.get();
        Pabellon pabellon = pabellonBusqueda.get();
        aula.setPabellon(pabellon);
        mensaje.put("success",Boolean.TRUE);
        mensaje.put("mensaje",service.save(aula));
        return ResponseEntity.ok(mensaje);
    }
}
