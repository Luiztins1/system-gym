package com.github.Luiztins1.model.mapper;

import com.github.Luiztins1.controller.dtos.RegistrationDTO;
import com.github.Luiztins1.model.entity.Registration;
import com.github.Luiztins1.model.entity.Student;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
public class RegistrationMapper {

    public static RegistrationDTO toDto(Registration registration){
        if(registration == null) return null;

        return new RegistrationDTO(
                registration.getId(),
                registration.getModality(),
                registration.getStudent().getId()
        );
    }

    public static Registration toEntity(RegistrationDTO registrationDTO){
        if(registrationDTO == null) return null;

        Registration registration = new Registration();
        Student student = registration.getStudent();

        registration.setId(registrationDTO.id());
        registration.setModality(registrationDTO.modality());
        registration.setStudent(student);

        return registration;
    }


}
