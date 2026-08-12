package com.biancasoares.transactionapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST) //esse método vai tratar um erro de validação, queremos que a resposta continue sendo HTTP 400.
    @ExceptionHandler //diz qual excecao sera tratada
    public Map<String,String> handleValidationErrors (MethodArgumentNotValidException exception){ // parametro vem da falha de @Valid
        Map<String, String> errors = new HashMap<>(); //criando map vazio

        for (FieldError error : exception.getBindingResult().getFieldErrors()){ //você percorre todos os campos que falharam na validação.
            errors.put(error.getField(), error.getDefaultMessage()); //você coloca cada erro no Map.
        }

        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public Map <String, String> handle404Error (TransactionNotFoundException exception ){
        Map<String, String> error = new HashMap <>();
        error.put("error", exception.getMessage());

        return error;


    }
}
