package com.github.Luiztins1.validator;

import com.github.Luiztins1.controller.dtos.PlanDTO;
import com.github.Luiztins1.model.entity.Plan;
import com.github.Luiztins1.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PlanValidator {

    private final PlanRepository planRepository;

    public Plan validateSource(UUID id){
        return planRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Plan validateIdForReturnNullMapper(PlanDTO planDTO){
        if(planDTO.id() == null) return null;
        return validateSource(planDTO.id());
    }
}
