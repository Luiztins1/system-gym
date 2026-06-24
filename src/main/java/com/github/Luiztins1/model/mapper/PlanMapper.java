package com.github.Luiztins1.model.mapper;

import com.github.Luiztins1.controller.dtos.PlanDTO;
import com.github.Luiztins1.model.entity.Plan;
import com.github.Luiztins1.model.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

    public static PlanDTO toEntity(Plan plan){
        if(plan == null) return null;

        return new PlanDTO(
                plan.getId(),
                plan.getValue(),
                plan.getTypePlan(),
                plan.getStudent_id().getId());
    }

    public Plan toDto(PlanDTO planDTO){
        if(planDTO == null) return null;

        Plan plan = new Plan();
        Student student = new Student();

        plan.setId(planDTO.id());
        plan.setValue(planDTO.value());
        plan.setTypePlan(planDTO.typePlan());
        plan.setStudent_id(student.getPlan_id().getStudent_id());

        return plan;
    }
}
