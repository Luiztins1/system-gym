package com.github.Luiztins1.model.mapper;

import com.github.Luiztins1.controller.dtos.StudentDTO;
import com.github.Luiztins1.model.entity.Registration;
import com.github.Luiztins1.model.entity.Student;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
public class StudentMapper {

    public static StudentDTO toDto(Student student){
        if(student == null) return null;

        UUID plan = (student.getPlanId() != null) ? student.getPlanId().getId() : null;

        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getCpf(),
                student.getAddress(),
                student.getTypeModality(),
                student.getRegistrationDate(),
                plan
        );
    }

    public static Student toEntity(StudentDTO studentDTO){
        if(studentDTO == null) return null;

        Student student = new Student();
        Registration registration = new Registration();

        student.setId(studentDTO.id());
        student.setName(studentDTO.name());
        student.setCpf(studentDTO.cpf());
        student.setAddress(studentDTO.address());
        student.setTypeModality(studentDTO.typeModality());
        student.setRegistrationDate(studentDTO.registrationDate());
        student.setPlanId(null);

        LocalDate plusDate = student.getRegistrationDate();

        registration.setModality(student.getTypeModality());
        registration.setRegistrationDate(student.getRegistrationDate());
        registration.setRegistrationValidity(plusDate.plusMonths(1));

        registration.setStudent(student);
        student.setRegistrationId(registration);
        return student;
    }


}
