package com.github.Luiztins1.model.mapper;

import com.github.Luiztins1.controller.dtos.StudentDTO;
import com.github.Luiztins1.model.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public static StudentDTO toDto(Student student){
        if(student == null) return null;

        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getCpf(),
                student.getPlan_id().getId() != null ? student.getPlan_id().getId() : null,
                student.getRegistration_id().getId() != null ? student.getRegistration_id().getId(): null
        );
    }

    public static Student toEntity(StudentDTO studentDTO){
        if(studentDTO == null) return null;

        Student student = new Student();

        student.setId(studentDTO.id());
        student.setCpf(studentDTO.cpf());
        student.setPlan_id(null);
        student.setRegistration_id(null);

        return student;
    }


}
