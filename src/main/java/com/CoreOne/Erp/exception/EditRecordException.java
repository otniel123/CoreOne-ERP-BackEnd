package com.CoreOne.Erp.exception;

public class EditRecordException extends Exception{

    public EditRecordException(){
        super("It was not possible to edit this register");
    }

    public EditRecordException(String message){
        super(message);
    }
}
