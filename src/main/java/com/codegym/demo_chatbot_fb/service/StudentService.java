package com.codegym.demo_chatbot_fb.service;


import com.codegym.demo_chatbot_fb.model.Student;

import java.util.Optional;

public interface StudentService{
    Iterable<Student> findAll();
    String save(Student t);
    void delete(Long id);

    Optional<Student> findById(Long id);
    Iterable<Student> searchStudentByPhoneNumber(String phoneNumber);
}
