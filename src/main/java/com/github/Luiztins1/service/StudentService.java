package com.github.Luiztins1.service;

import com.github.Luiztins1.controller.dtos.StudentDTO;
import com.github.Luiztins1.model.entity.Plan;
import com.github.Luiztins1.model.entity.Student;
import com.github.Luiztins1.model.mapper.StudentMapper;
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
    public Student registerStudent(StudentDTO studentDTO){
        Student student = StudentMapper.toEntity(studentDTO);

        if(studentDTO.planId() != null){
            Plan plan = planValidator.validateSource(studentDTO.planId());
            student.setPlanId(plan);
        }

        studentValidator.validateStudentDuplicate(student);
        return studentRepository.save(student);
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    @Transactional
    public Optional<Student> updateStudent(UUID id, StudentDTO studentDTO){
        return studentRepository.findById(id)
                .map(student -> {
                    student.setAddress(studentDTO.address());
                    student.setTypeModality(studentDTO.typeModality());

                    if(studentDTO.planId() != null){
                        Plan newPlan = planValidator.validateSource(studentDTO.planId());
                        student.setPlanId(newPlan);
                    }

                    if(student.getRegistrationId() != null){
                       student.getRegistrationId().setModality(studentDTO.typeModality());
                    }
                    return studentRepository.save(student);
                });
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
