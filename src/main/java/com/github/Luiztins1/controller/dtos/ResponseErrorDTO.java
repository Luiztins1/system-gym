package com.github.Luiztins1.controller.dtos;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ResponseErrorDTO(
        HttpStatus error,
        String messageError,
        List<FieldErrorDTO> fieldErrorList) {

    public static ResponseErrorDTO duplicateError(String messageError){
        return new ResponseErrorDTO(HttpStatus.CONFLICT, messageError, List.of());
    }
}
