package com.github.Luiztins1.controller.dtos;

import com.github.Luiztins1.model.enums.TypeModality;

import java.util.UUID;

public record StudentDTO(
        UUID id,
        String name,
        String cpf,
        String address,
        TypeModality typeModality,
        UUID planId) {
}
