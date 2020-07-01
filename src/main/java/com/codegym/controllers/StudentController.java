package com.codegym.controllers;

import com.codegym.models.Student;
import com.codegym.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping()
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> students = studentService.findAll();

        ResponseEntity<List<Student>> responseEntity
                = new ResponseEntity(students, HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getOneStudent(@PathVariable Long id) {
        Student student = studentService.findOne(id);

        ResponseEntity<Student> responseEntity
                = new ResponseEntity(student, HttpStatus.OK);

        return responseEntity;
    }

    @PostMapping()
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.save(student);

        ResponseEntity<Student> responseEntity
                = new ResponseEntity(savedStudent, HttpStatus.OK);

        return responseEntity;
    }

}
