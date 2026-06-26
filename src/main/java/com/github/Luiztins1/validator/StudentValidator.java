package com.github.Luiztins1.validator;

import com.github.Luiztins1.controller.dtos.StudentDTO;
import com.github.Luiztins1.exceptions.DuplicateException;
import com.github.Luiztins1.exceptions.NotFoundException;
import com.github.Luiztins1.model.entity.Student;
import com.github.Luiztins1.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentValidator {

    private final StudentRepository studentRepository;

    public Student validateSource(UUID id){
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado."));
    }

    public void validateStudentDuplicate(Student student){
        if(duplicateStudent(student)) throw new DuplicateException("Aluno já cadastrado no sistema.");
    }

    private boolean duplicateStudent(Student student){
        return studentRepository.existsByIdOrCpf(student.getId(), student.getCpf());
    }
}
