package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aula")
public class AulaController extends GenericController<Aula, AulaDAO> {
    private PabellonDAO pabellonDAO;
    public AulaController(AulaDAO service, PabellonDAO pabellonDAO) {
        super(service);
        this.pabellonDAO = pabellonDAO;
    }
    @GetMapping("/pizarron")
    public List<Aula> findAulasByPizarron(@RequestParam Pizarron query){
        List<Aula> aulas = (List<Aula>) service.buscarPorTipoPizarron(query);
        if(aulas.isEmpty()){
            throw new BadRequestException("No hay aulas con pizarron del tipo "+query);
        }
        return aulas;
    }

    @GetMapping("/pabellon-nombre")
    public List<Aula> findAulasByPabellonNombre (@RequestParam String query){
        List<Aula> aulas = (List<Aula>) service.buscarPorNombrePabellon(query);
        if(aulas.isEmpty()){
            throw new BadRequestException("No hay aulas con pertencientes al pabellon "+query);
        }
        return aulas;
    }

    @GetMapping("/numero-aula")
    public Aula findAulaByNroAula(@RequestParam Integer query){
        Optional<Aula> aulas = service.buscarPorNumeroDeAula(query);
        if(!aulas.isPresent()){
            throw new BadRequestException("No hay aulas con el numero "+query);
        }
        return aulas.get();
    }

    @PutMapping("/{id}")
    public Aula editar (@PathVariable Integer id,@RequestBody Aula aula){
        Optional<Aula> byId = service.findById(id);
        if(!byId.isPresent()){
            throw new BadRequestException("No existe aula con el id "+id);
        }
        Aula aulaGuardar = byId.get();
        aulaGuardar.setCantidadPupitres(aula.getCantidadPupitres());
        aulaGuardar.setPizarron(aula.getPizarron());
        aulaGuardar.setMedidas(aula.getMedidas());
        return service.save(aulaGuardar);
    }
    @PutMapping("/{idAula}/pabellon/{idPabellon}")
    public Aula asignarAPabellon(@PathVariable Integer idAula,@PathVariable Integer idPabellon){
        Optional<Aula> aulaBusqueda = service.findById(idAula);
        Optional<Pabellon> pabellonBusqueda = pabellonDAO.findById(idPabellon);
        if(!aulaBusqueda.isPresent()){
            throw new BadRequestException("No existe aula con id "+idAula);
        }
        if(!pabellonBusqueda.isPresent()){
            throw new BadRequestException("No existe el pabellon con id "+idPabellon);
        }
        Aula aula = aulaBusqueda.get();
        Pabellon pabellon = pabellonBusqueda.get();
        aula.setPabellon(pabellon);
        return service.save(aula);
    }
}
