package com.github.Luiztins1.controller.rest;

import com.github.Luiztins1.controller.dtos.RegistrationDTO;
import com.github.Luiztins1.model.entity.Registration;
import com.github.Luiztins1.model.mapper.RegistrationMapper;
import com.github.Luiztins1.service.RegistrationService;
import jakarta.servlet.Servlet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationDTO> registerRegistration(@RequestBody RegistrationDTO registrationDTO){
        Registration registration = registrationService.registerRegistration(registrationDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(registration.getId())
                .toUri();

        return ResponseEntity.created(location).body(RegistrationMapper.toDto(registration));
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDTO>> findAll(){
        List<RegistrationDTO> registrationList = registrationService.findAll()
                .stream()
                .map(RegistrationMapper::toDto)
                .toList();

        if(registrationList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(registrationList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDTO> updateRegistration(@PathVariable UUID id, @RequestBody RegistrationDTO registrationDTO){
        Optional<Registration> registrationOptional = registrationService.updateRegistration(registrationDTO);

        if(registrationOptional.isPresent()){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDTO> findById(@PathVariable UUID id){
        return registrationService.findById(id)
                .map(RegistrationMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
