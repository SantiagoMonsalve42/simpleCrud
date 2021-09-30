package com.springsimplespasos.universidad.universidadbackend;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Aula;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Carrera;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Direccion;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.AulaDAO;
import com.springsimplespasos.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class CarreraComandos implements CommandLineRunner {

    @Autowired
    private AulaDAO servicio;

    @Override
    public void run(String... args) throws Exception {
    /*
        Pizarron p1 = Pizarron.PIZARRA_BLANCA;
        Pizarron p2 = Pizarron.PIZARRA_TIZA;
        Aula a1 = new Aula(1,10,"medidas",30,p1);
        Aula a2 = new Aula(2,11,"medidas",20,p1);
        Aula a3 = new Aula(3,12,"medidas",31,p1);
        Aula a4 = new Aula(4,13,"medidas",31,p2);
        Aula a5 = new Aula(5,14,"medidas",32,p2);
        Aula a6 = new Aula(6,15,"medidas",34,p2);
        Aula a7 = new Aula(7,16,"medidas",35,p2);
        Direccion d1 = new Direccion("calle","numerp","5050","dpt","101","lo");
        Direccion d2 = new Direccion("calle","numerp","5050","dpt","101","los");
        Pabellon pa1 = new Pabellon(null,2.3,"sur",d1);
        Pabellon pa2 = new Pabellon(null,2.8,"la 401",d2);
        Set<Aula> lista= null;
        Set<Aula> lista2= null;
        lista.add(a1);
        lista.add(a2);
        lista.add(a3);
        lista.add(a4);
        lista2.add(a5);
        lista2.add(a6);
        lista2.add(a7);
        pa1.setAulas(lista);
        pa2.setAulas(lista2);


        Optional<Aula> n=servicio.buscarPorNumeroDeAula(10);
        System.out.println(n);*/
    }
}
