package com.github.Luiztins1.controller.dtos;

import com.github.Luiztins1.model.enums.TypeModality;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record RegistrationDTO(
        UUID id,

        @NotNull(message = "Preencha o campo corretamente.")
        TypeModality modality,

        @NotNull(message = "Preencha o campo corretamente.")
        UUID studentId) {
}
