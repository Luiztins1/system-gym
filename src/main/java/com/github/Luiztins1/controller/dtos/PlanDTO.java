package com.github.Luiztins1.controller.dtos;

import com.github.Luiztins1.model.enums.TypePlan;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PlanDTO(
        UUID id,
        BigDecimal value,
        TypePlan typePlan,
        List<UUID> studentId) {
}
