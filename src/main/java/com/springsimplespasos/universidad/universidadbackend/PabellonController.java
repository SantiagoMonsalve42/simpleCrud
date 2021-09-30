package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PabellonController implements CommandLineRunner {

    @Autowired
    private PabellonDAO service;
    @Override
    public void run(String... args) throws Exception {
        /*Direccion d1 = new Direccion("calle","numerp","5050","dpt","101","lo");
        Pabellon p = new Pabellon(8,2.6,"la 40",d1);
        service.save(p);*/
        Iterable<Pabellon> p=service.buscarPorNombre("la 40");
        p.forEach(System.out::println);
    }
}
