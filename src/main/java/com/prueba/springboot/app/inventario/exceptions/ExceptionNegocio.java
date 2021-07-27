package com.prueba.springboot.app.inventario.exceptions;

public class ExceptionNegocio extends RuntimeException {

    public ExceptionNegocio(String message){
        super(message);
    }
}
