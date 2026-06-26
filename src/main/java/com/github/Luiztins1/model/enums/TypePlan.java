package com.github.Luiztins1.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public enum TypePlan {
    COMUM(69.90),
    ALUNO_PREMIUM(129.90);

    private final BigDecimal value;

    TypePlan(double value) {
        this.value = BigDecimal.valueOf(value);
    }
}
