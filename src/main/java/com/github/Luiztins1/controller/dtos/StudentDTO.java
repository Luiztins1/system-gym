package com.github.Luiztins1.controller.dtos;

import com.github.Luiztins1.model.enums.TypeModality;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

public record StudentDTO(
        UUID id,

        @Size(min = 6, max = 128)
        @NotBlank(message = "Preencha o campo corretamente.")
        String name,

        @CPF
        @NotBlank(message = "Preencha o campo corretamente")
        String cpf,

        @Size(min = 6, max = 128)
        @NotBlank(message = "Preencha o campo corretamente.")
        String address,

        @NotNull(message = "Preencha o campo corretamente.")
        TypeModality typeModality,

        LocalDate registrationDate,

        @NotNull(message = "Preencha o campo corretamente.")
        UUID planId) {
}
