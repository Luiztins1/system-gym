package com.github.Luiztins1.service;

import com.github.Luiztins1.controller.dtos.PlanDTO;
import com.github.Luiztins1.model.entity.Plan;
import com.github.Luiztins1.repository.PlanRepository;
import com.github.Luiztins1.validator.PlanValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanValidator planValidator;

    @Transactional
    public Plan registerPlan(PlanDTO planDTO){
        Plan plan = new Plan(
                null,
                planDTO.value(),
                planDTO.typePlan(),
                null
        );

        planValidator.validateDuplicatePlan(plan);
        return planRepository.save(plan);
    }

    public List<Plan> findAll(){
        return planRepository.findAll();
    }

    @Transactional
    public Optional<Plan> updatePlan(PlanDTO planDTO){
        Plan plan = planValidator.validateSource(planDTO.id());

        plan.setValue(planDTO.value());
        plan.setTypePlan(planDTO.typePlan());

        return Optional.of(planRepository.save(plan));
    }

    @Transactional
    public void cancelRegisterPlan(UUID id){
        planValidator.validateSource(id);
        planRepository.deleteById(id);
    }

    public Optional<Plan> findById(UUID id){
        return Optional.of(planValidator.validateSource(id));
    }
}
