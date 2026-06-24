package com.github.Luiztins1.model.mapper;

import com.github.Luiztins1.controller.dtos.StudentDTO;
import com.github.Luiztins1.model.entity.Plan;
import com.github.Luiztins1.model.entity.Registration;
import com.github.Luiztins1.model.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public static StudentDTO toEntity(Student student){
        if(student == null) return null;

        return new StudentDTO(
                student.getId(),
                student.getCpf(),
                student.getPlan_id().getId() != null ? student.getPlan_id().getId() : null,
                student.getRegistration_id().getId() != null ? student.getRegistration_id().getId(): null
        );
    }

    public Student toDto(StudentDTO studentDTO){
        if(studentDTO == null) return null;

        Student student = new Student();
        Plan plan = new Plan();
        Registration registration = new Registration();

        student.setId(studentDTO.id());
        student.setCpf(studentDTO.cpf());
        student.setPlan_id(plan);
        student.setRegistration_id(registration);

        return student;
    }
}
