package com.codegym.services;

import com.codegym.models.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();
    Student findOne(Long id);
    Student save(Student student);
    Student removeById(Long id);

}
