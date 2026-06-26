package com.github.Luiztins1.service;

import com.github.Luiztins1.controller.dtos.RegistrationDTO;
import com.github.Luiztins1.model.entity.Registration;
import com.github.Luiztins1.repository.RegistrationRepository;
import com.github.Luiztins1.validator.RegistrationValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationValidator registrationValidator;
    private final RegistrationRepository registrationRepository;

    @Deprecated
    @Transactional
    public Registration registerRegistration(RegistrationDTO registrationDTO){
        Registration registration = new Registration(
                null,
                registrationDTO.modality(),
                null,
                null
        );
        registrationValidator.validateDuplicationRegistration(registration);
        return registrationRepository.save(registration);
    }

    public List<Registration> findAll(){
        return registrationRepository.findAll();
    }

    @Transactional
    public Optional<Registration> updateRegistration(RegistrationDTO registrationDTO){
        Registration registration = registrationValidator.validateSource(registrationDTO.id());
        registration.setModality(registrationDTO.modality());

        return Optional.of(registrationRepository.save(registration));
    }

    public Optional<Registration> findById(UUID id){
        return Optional.of(registrationValidator.validateSource(id));
    }
}
