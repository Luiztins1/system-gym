package com.github.Luiztins1.controller.dtos;

import com.github.Luiztins1.model.enums.TypePlan;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PlanDTO(
        UUID id,

        BigDecimal value,

        @NotNull(message = "Preencha o campo corretamente.")
        TypePlan typePlan,
        
        List<UUID> studentId) {
}
