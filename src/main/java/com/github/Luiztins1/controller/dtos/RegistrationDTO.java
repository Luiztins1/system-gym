package com.github.Luiztins1.controller.dtos;

import com.github.Luiztins1.model.enums.TypeModality;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record RegistrationDTO(
        UUID id,

        @NotNull(message = "Preencha o campo corretamente.")
        TypeModality modality,

        @FutureOrPresent(message = "A data de início do plano não pode ser retroativa.")
        LocalDate registrationDate,

        LocalDate registrationValidity,

        @NotNull(message = "Preencha o campo corretamente.")
        UUID studentId) {
}
