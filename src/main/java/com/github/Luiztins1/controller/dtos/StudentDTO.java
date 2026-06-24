package com.github.Luiztins1.controller.dtos;

import java.util.List;
import java.util.UUID;

public record StudentDTO(
        UUID id,
        String cpf,
        UUID planId,
        List<UUID> listRegistration) {
}
