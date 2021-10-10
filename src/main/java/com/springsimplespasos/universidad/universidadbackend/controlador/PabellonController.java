package com.springsimplespasos.universidad.universidadbackend.controlador;

import com.springsimplespasos.universidad.universidadbackend.exceptions.BadRequestException;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pabellon")
public class PabellonController extends GenericController<Pabellon, PabellonDAO> {

    public PabellonController(PabellonDAO service) {
        super(service);
    }
    @GetMapping("/localidad")
    public List<Pabellon> buscarPabellonesPorLocalidad(@RequestParam String query){
        List<Pabellon> pabellons =(List<Pabellon>)service.buscarPorLocalidad(query);
        if(pabellons.isEmpty()){
            throw new BadRequestException("No existen pabellones en la localidad "+query);
        }
        return pabellons;
    }
    @GetMapping("/nombre")
    public List<Pabellon> buscarPabellonesPorNombre(@RequestParam String query){
        List<Pabellon> pabellons =(List<Pabellon>)service.buscarPorNombre(query);
        if(pabellons.isEmpty()){
            throw new BadRequestException("No existen pabellones en la localidad "+query);
        }
        return pabellons;
    }

    @PutMapping("/{id}")
    public Pabellon editar(@PathVariable Integer id,@RequestBody Pabellon pabellon){
        Optional<Pabellon> pabellonBusqueda= service.findById(id);
        if(!pabellonBusqueda.isPresent()){
            throw new BadRequestException("No existe pabellon con ID"+id);
        }
        Pabellon pabellonEditable = pabellonBusqueda.get();
        pabellonEditable.setDireccion(pabellon.getDireccion());
        pabellonEditable.setNombre(pabellon.getNombre());
        pabellonEditable.setMts2(pabellon.getMts2());
        return service.save(pabellonEditable);
    }


}
