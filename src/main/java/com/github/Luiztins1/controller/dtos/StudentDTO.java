package com.github.Luiztins1.controller.dtos;

import com.github.Luiztins1.model.enums.TypeModality;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record StudentDTO(
        UUID id,

        @Size(min = 6, max = 128)
        @NotBlank(message = "Preencha o campo corretamente.")
        String name,

        @Size(min = 11, max = 11)
        @NotBlank(message = "Preencha o campo corretamente")
        String cpf,

        @Size(min = 6, max = 128)
        @NotBlank(message = "Preench o campo corretamente.")
        String address,

        @NotNull(message = "Preencha o campo corretamente.")
        TypeModality typeModality,

        @NotNull(message = "Preencha o campo corretamente.")
        UUID planId) {
}
