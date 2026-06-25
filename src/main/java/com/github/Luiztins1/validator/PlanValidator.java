package com.github.Luiztins1.validator;

import com.github.Luiztins1.controller.dtos.PlanDTO;
import com.github.Luiztins1.controller.dtos.StudentDTO;
import com.github.Luiztins1.model.entity.Plan;
import com.github.Luiztins1.model.entity.Student;
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

    public void validateDuplicatePlan(Plan plan){
        if(duplicatePlan(plan)) throw new RuntimeException();
    }

    private boolean duplicatePlan(Plan plan){
        return planRepository.existsByTypePlan(plan.getTypePlan());
    }
}
