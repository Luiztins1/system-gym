package com.github.Luiztins1.model.mapper;

import com.github.Luiztins1.controller.dtos.RegistrationDTO;
import com.github.Luiztins1.model.entity.Registration;
import com.github.Luiztins1.model.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    public static RegistrationDTO toDto(Registration registration){
        if(registration == null) return null;

        return new RegistrationDTO(
                registration.getId(),
                registration.getModality(),
                registration.getStudent_id().getId() != null ? registration.getStudent_id().getId() : null
        );
    }

    public static Registration toEntity(RegistrationDTO registrationDTO){
        if(registrationDTO == null) return null;

        Registration registration = new Registration();

        registration.setId(registrationDTO.id());
        registration.setModality(registrationDTO.modality());
        registration.setStudent_id(null);

        return registration;
    }


}
