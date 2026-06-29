package com.github.Luiztins1.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record UserAuthDTO(

        UUID id,

        @NotBlank(message = "O login é obrigatório e não pode estar em branco.")
        String login,

        @NotBlank(message = "A senha é obrigatória e não pode estar em branco.")
        String password,

        @NotEmpty(message = "Adicione corretamente as roles.")
        List<String> roles) {
}
