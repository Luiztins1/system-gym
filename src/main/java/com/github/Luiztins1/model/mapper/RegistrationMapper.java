package com.github.Luiztins1.model.mapper;

import com.github.Luiztins1.controller.dtos.RegistrationDTO;
import com.github.Luiztins1.model.entity.Registration;
import com.github.Luiztins1.model.entity.Student;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
public class RegistrationMapper {


    public static RegistrationDTO toDto(Registration registration){
        if(registration == null) return null;

        UUID studentId = (registration.getStudent() != null) ? registration.getStudent().getId() : null;

        return new RegistrationDTO(
                registration.getId(),
                registration.getModality(),
                registration.getRegistrationDate(),
                registration.getRegistrationValidity(),
                studentId
        );
    }

    public static Registration toEntity(RegistrationDTO registrationDTO){
        if(registrationDTO == null) return null;

        Registration registration = new Registration();
        Student student = registration.getStudent();


        registration.setId(registrationDTO.id());
        registration.setModality(registrationDTO.modality());
        registration.setRegistrationDate(registrationDTO.registrationDate());
        registration.setRegistrationValidity(registrationDTO.registrationValidity());
        registration.setStudent(student);

        return registration;
    }
}
