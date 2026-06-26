package com.github.Luiztins1.controller.rest;

import com.github.Luiztins1.controller.dtos.StudentDTO;
import com.github.Luiztins1.model.entity.Student;
import com.github.Luiztins1.model.mapper.StudentMapper;
import com.github.Luiztins1.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> registerStudent(@RequestBody @Valid StudentDTO studentDTO){
        Student student = studentService.registerStudent(studentDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.getId())
                .toUri();

        return ResponseEntity.created(location).body(StudentMapper.toDto(student));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll(){
        List<StudentDTO> studentList = studentService.findAll()
                .stream()
                .map(StudentMapper::toDto)
                .toList();

        if(studentList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(studentList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable UUID id, @RequestBody @Valid StudentDTO studentDTO){
        Optional<Student> studentOptional = studentService.updateStudent(id, studentDTO);

        if(studentOptional.isPresent()){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelRegisterStudent(@PathVariable UUID id){
        studentService.cancelRegisterStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable UUID id){
        return studentService.findById(id)
                .map(StudentMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
