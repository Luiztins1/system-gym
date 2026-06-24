package com.github.Luiztins1.validator;

import com.github.Luiztins1.controller.dtos.PlanDTO;
import com.github.Luiztins1.controller.dtos.RegistrationDTO;
import com.github.Luiztins1.model.entity.Plan;
import com.github.Luiztins1.model.entity.Registration;
import com.github.Luiztins1.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationValidator {

    private final RegistrationRepository registrationRepository;

    public Registration validateSource(UUID id){
        return registrationRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Registration validateIdForReturnNullMapper(RegistrationDTO registrationDTO){
        if(registrationDTO.id() == null) return null;
        return validateSource(registrationDTO.id());
    }
}
