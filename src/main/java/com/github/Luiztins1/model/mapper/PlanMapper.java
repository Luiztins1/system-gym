package com.github.Luiztins1.model.mapper;

import com.github.Luiztins1.controller.dtos.PlanDTO;
import com.github.Luiztins1.controller.dtos.StudentDTO;
import com.github.Luiztins1.model.entity.Plan;
import com.github.Luiztins1.model.entity.Student;
import com.github.Luiztins1.validator.PlanValidator;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PlanMapper {

    public static PlanDTO toDto(Plan plan){
        if(plan == null) return null;

        return new PlanDTO(
                plan.getId(),
                plan.getValue(),
                plan.getTypePlan(),
                plan.getStudent_id().getId() != null ? plan.getStudent_id().getId() : null
        );
    }

    public static Plan toEntity(PlanDTO planDTO){
        if(planDTO == null) return null;

        Plan plan = new Plan();

        plan.setId(planDTO.id());
        plan.setValue(planDTO.value());
        plan.setTypePlan(planDTO.typePlan());
        plan.setStudent_id(null);

        return plan;
    }
}
