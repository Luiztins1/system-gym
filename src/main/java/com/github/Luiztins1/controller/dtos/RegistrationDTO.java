package com.github.Luiztins1.controller.dtos;

import com.github.Luiztins1.model.enums.TypeModality;

import java.util.List;
import java.util.UUID;

public record RegistrationDTO(
        UUID id,
        TypeModality modality,
        UUID studentId) {
}
