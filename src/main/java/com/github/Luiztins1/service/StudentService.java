package com.github.Luiztins1.service;

import com.github.Luiztins1.controller.dtos.PlanDTO;
import com.github.Luiztins1.controller.dtos.RegistrationDTO;
import com.github.Luiztins1.controller.dtos.StudentDTO;
import com.github.Luiztins1.model.entity.Plan;
import com.github.Luiztins1.model.entity.Registration;
import com.github.Luiztins1.model.entity.Student;
import com.github.Luiztins1.repository.StudentRepository;
import com.github.Luiztins1.validator.PlanValidator;
import com.github.Luiztins1.validator.RegistrationValidator;
import com.github.Luiztins1.validator.StudentValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentValidator studentValidator;
    private final StudentRepository studentRepository;
    private final PlanValidator planValidator;
    private final RegistrationValidator registrationValidator;

    @Transactional
    public Student registerStudent(StudentDTO studentDTO, UUID planId, UUID registrationId){
        Plan plan = planValidator.validateSource(planId);
        Registration registration = registrationValidator.validateSource(registrationId);

        Student student = new Student(
                null,
                studentDTO.cpf(),
                plan,
                registration
        );

        studentValidator.validateStudentDuplicate(student);
        return studentRepository.save(student);
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    @Transactional
    public Optional<Student> updateStudent(UUID studentId, UUID planId, UUID registrationId){
        Student student = studentValidator.validateSource(studentId);

        Plan plan = planValidator.validateSource(planId);
        Registration registration = registrationValidator.validateSource(registrationId);

        student.setPlan_id(plan);
        student.setRegistration_id(registration);

        return Optional.of(studentRepository.save(student));
    }

    @Transactional
    public void cancelRegisterStudent(UUID id){
        studentValidator.validateSource(id);
        studentRepository.deleteById(id);
    }

    public Optional<Student> findById(UUID id){
        return Optional.of(studentValidator.validateSource(id));
    }
}
