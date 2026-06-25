package com.github.Luiztins1.repository;

import com.github.Luiztins1.model.entity.Registration;
import com.github.Luiztins1.model.enums.TypeModality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
    boolean existsByIdOrModality(UUID id, TypeModality typeModality);
}
