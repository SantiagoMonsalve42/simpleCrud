package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/aula")
public class AulaController extends GenericController<Aula, AulaDAO> {
    private Map<String,Object> mensaje;
    private PabellonDAO pabellonDAO;
    public AulaController(AulaDAO service, PabellonDAO pabellonDAO) {
        super(service);
        this.pabellonDAO = pabellonDAO;
    }
    @GetMapping("/pizarron")
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
