package com.github.Luiztins1.model.mapper;

import com.github.Luiztins1.controller.dtos.RegistrationDTO;
import com.github.Luiztins1.model.entity.Registration;
import com.github.Luiztins1.model.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    public static RegistrationDTO toEntity(Registration registration){
        if(registration == null) return null;

        return new RegistrationDTO(
                registration.getId(),
                registration.getModality(),
                registration.getStudent_id().getId()
        );
    }

    public Registration toDto(RegistrationDTO registrationDTO){
        if(registrationDTO == null) return null;

        Registration registration = new Registration();
        Student student = new Student();
        registration.setId(registrationDTO.id());
        registration.setModality(registrationDTO.modality());
        registration.setStudent_id(student.getRegistration_id().getStudent_id());

        return registration;
    }
}
