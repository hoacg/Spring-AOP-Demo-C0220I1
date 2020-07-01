package com.codegym.repositories;

import com.codegym.models.Student;
import com.codegym.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student findOne(Long id) {
        return studentRepository.findOne(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student removeById(Long id) {
        Student student = studentRepository.findOne(id);
        studentRepository.delete(id);

        return student;
    }
}
