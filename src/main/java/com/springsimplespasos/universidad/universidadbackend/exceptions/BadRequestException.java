package com.springsimplespasos.universidad.universidadbackend.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
