package com.github.Luiztins1.repository;

import com.github.Luiztins1.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    boolean existsByIdOrCpf(UUID id, String cpf);
    Student existsByNameOrCpf(String name, String cpf);
}
