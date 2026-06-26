package com.github.Luiztins1.validator;

import com.github.Luiztins1.controller.dtos.PlanDTO;
import com.github.Luiztins1.controller.dtos.StudentDTO;
import com.github.Luiztins1.exceptions.DuplicateException;
import com.github.Luiztins1.exceptions.NotFoundException;
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
        return planRepository.findById(id).orElseThrow(() -> new NotFoundException("Plano não encontrado."));
    }

    public void validateDuplicatePlan(Plan plan){
        if(duplicatePlan(plan)) throw new DuplicateException("Plano já cadastrado no sistema.");
    }

    private boolean duplicatePlan(Plan plan){
        return planRepository.existsByTypePlan(plan.getTypePlan());
    }
}
