package com.github.Luiztins1.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserAuthDTO(

        @NotNull(message = "Preencha o campo corretamente.")
        String login,

        @NotNull(message = "Preencha o campo corretamente.")
        String password,

        @NotBlank(message = "Adicione corretamente as roles.")
        List<String> roles) {
}
