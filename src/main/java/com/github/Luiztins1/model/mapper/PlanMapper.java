package com.github.Luiztins1.model.mapper;

import com.github.Luiztins1.controller.dtos.PlanDTO;
import com.github.Luiztins1.controller.dtos.StudentDTO;
import com.github.Luiztins1.model.entity.Plan;
import com.github.Luiztins1.model.entity.Student;
import com.github.Luiztins1.validator.PlanValidator;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
public class PlanMapper {

    public static PlanDTO toDto(Plan plan){
        if(plan == null) return null;

        List<UUID> studentList = plan.getStudentList() != null
                ? plan.getStudentList()
                .stream()
                .map(Student::getId)
                .toList() : null;

        return new PlanDTO(
                plan.getId(),
                plan.getValue(),
                plan.getTypePlan(),
                studentList
        );
    }

    public static Plan toEntity(PlanDTO planDTO){
        if(planDTO == null) return null;

        Plan plan = new Plan();

        plan.setId(planDTO.id());
        plan.setValue(planDTO.typePlan().getValue());
        plan.setTypePlan(planDTO.typePlan());
        plan.setStudentList(null);

        return plan;
    }
}
