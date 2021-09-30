package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Persona;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.EmpleadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CarreraComandos implements CommandLineRunner {

    @Autowired
    private EmpleadoDAO servicio;

    @Override
    public void run(String... args) throws Exception {
        TipoEmpleado t1 = TipoEmpleado.ADMINISTRATIVO;
        Iterable<Persona> n = servicio.findEmpleadoByTipoEmpleado(t1);
        n.forEach(System.out::println);
    }
}
