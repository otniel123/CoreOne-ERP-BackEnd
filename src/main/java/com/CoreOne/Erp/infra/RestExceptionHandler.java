package com.CoreOne.Erp.infra;

import com.CoreOne.Erp.exception.EditRecordException;
import com.CoreOne.Erp.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<String> entityNotFoundHandler(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(EditRecordException.class)
    private ResponseEntity<String> editRecordException(EditRecordException ex){
        return ResponseEntity.status(500).body(ex.getMessage());
    }
}
