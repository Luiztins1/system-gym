package com.github.Luiztins1.validator;

import com.github.Luiztins1.controller.dtos.PlanDTO;
import com.github.Luiztins1.controller.dtos.RegistrationDTO;
import com.github.Luiztins1.controller.dtos.StudentDTO;
import com.github.Luiztins1.exceptions.DuplicateException;
import com.github.Luiztins1.exceptions.NotFoundException;
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
        return registrationRepository.findById(id).orElseThrow(() -> new NotFoundException("Matrícula não encontada."));
    }

    public void validateDuplicationRegistration(Registration registration){
       if(duplicateRegistration(registration)) throw new DuplicateException("Matrícula já cadastrado no sistema.");
    }


    private boolean duplicateRegistration(Registration registration){
        return registrationRepository.existsByIdOrModality(registration.getId(), registration.getModality());
    }
}
