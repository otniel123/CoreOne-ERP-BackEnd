package com.CoreOne.Erp.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(){
        super("Registro não encontrado");
    }

    public EntityNotFoundException(String message){
        super(message);
    }
}
