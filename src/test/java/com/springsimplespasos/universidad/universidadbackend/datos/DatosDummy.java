package com.springsimplespasos.universidad.universidadbackend.datos;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.*;
import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;

import java.math.BigDecimal;

import static com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado.*;

public class DatosDummy {
    public static Carrera carrera01(boolean conId){
        Carrera carrera = (conId) ?  new Carrera(1,"Ingenieria ambiental",50,5):
        new Carrera(null,"Ingenieria ambiental",50,5);
        return carrera;
    }
    public static Carrera carrera02(boolean conId){
        Carrera carrera = (conId) ? new Carrera(2,"Licenciatura ambiental",45,5):
         new Carrera(null,"Licenciatura ambiental",45,5);
        return carrera;
    }
    public static Carrera carrera03(boolean conId){
        Carrera carrera = (conId) ? new Carrera(3,"Ingenieria en Sistemas",70,5):
        new Carrera(null,"Ingenieria en Sistemas",70,5);
        return carrera;
    }
    public static Persona empleado01(){
        return new Empleado(null,"ANDRES","SANTIAGO","20201010",new Direccion(),new BigDecimal("452415"), ADMINISTRATIVO);
    }
    public static Persona empleado02(){
        return new Empleado(null,"JUAN","MARTIN","20201111",new Direccion(),new BigDecimal("452415"), MANTENIMIENTO);
    }
    public static Persona empleado03(){
        return new Empleado(null,"DANIEL","ESTEBAN","20201011",new Direccion(),new BigDecimal("452415"), ADMINISTRATIVO);
    }
    public static Persona profesor01(){
        return new Profesor(null,"HECTOR","FLOREZ","79444056",new Direccion(),new BigDecimal("4500000"));
    }
    public static Persona profesor02(){
        return new Profesor(null,"LUIS","ESMERALDA","79444057",new Direccion(),new BigDecimal("4500000"));
    }
    public static Persona profesor03(){
        return new Profesor(null,"HECTOR","MENDOZA","79444058",new Direccion(),new BigDecimal("4500000"));
    }
    public static Persona alumno01(){
        return new Alumno(null,"MARIO","RUIZ","333333",new Direccion());
    }
    public static Persona alumno02(){
        return new Alumno(null,"MATEO","CLAVIJAS","333334",new Direccion());
    }
    public static Persona alumno03(){
        return new Alumno(null,"LUIS","RUIZ","333335",new Direccion());
    }
}
