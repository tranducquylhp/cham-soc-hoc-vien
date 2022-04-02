package com.codegym.demo_chatbot_fb.service.impl;

import com.codegym.demo_chatbot_fb.model.Student;
import com.codegym.demo_chatbot_fb.repository.StudentRepository;
import com.codegym.demo_chatbot_fb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAllActive();
    }

    @Override
    public String save(Student student) {
        if (!StringUtils.hasText(student.getName())
        || !StringUtils.hasText(student.getPhoneNumber())
        || !StringUtils.hasText(student.getSchedule())
        || student.getDateRegister() == null
        || student.getClassNumber() == null
        || student.getMonthRegister() == null) return "Bạn chưa điền các thông tin bắt buộc";
        if (student.getId() == null) {
            student.setStatus(true);
        }
        Calendar c = Calendar.getInstance();
        c.setTime(student.getDateRegister());
        c.add(Calendar.MONTH, 1);
        student.setDateExpired(c.getTime());
        student.setCreateDate(new Date());
        studentRepository.save(student);
        return "";
    }

    @Override
    public void delete(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setStatus(false);
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Iterable<Student> searchStudentByPhoneNumber(String phoneNumber) {
        return null;
    }
}
