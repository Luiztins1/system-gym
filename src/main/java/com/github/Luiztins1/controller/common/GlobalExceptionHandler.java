package com.github.Luiztins1.controller.common;

import com.github.Luiztins1.controller.dtos.FieldErrorDTO;
import com.github.Luiztins1.controller.dtos.ResponseErrorDTO;
import com.github.Luiztins1.exceptions.DuplicateException;
import com.github.Luiztins1.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO httpMessageNotReadableException (HttpMessageNotReadableException e){
        return new ResponseErrorDTO(HttpStatus.BAD_REQUEST, "Erro de leitura de JSON ou valor inválido.", List.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO methodArgumentNotValidException (MethodArgumentNotValidException e){
        List<FieldErrorDTO> fieldErrorDTOList =
                e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error -> new FieldErrorDTO(error.getField(), error.getDefaultMessage()))
                        .toList();
        return new ResponseErrorDTO(HttpStatus.BAD_REQUEST, "Campos com valores inválidos ou vazios.", fieldErrorDTOList);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseErrorDTO dataIntegrityViolationException (DataIntegrityViolationException e){
        return new ResponseErrorDTO(HttpStatus.CONFLICT, "Erro de integridade ou restrição de banco de dados.", List.of());
    }

    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseErrorDTO duplicateException (DuplicateException e){
        return new ResponseErrorDTO(HttpStatus.CONFLICT, e.getMessage(), List.of());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseErrorDTO notFoundException (NotFoundException e){
        return new ResponseErrorDTO(HttpStatus.NOT_FOUND, e.getMessage(), List.of());
    }
}
