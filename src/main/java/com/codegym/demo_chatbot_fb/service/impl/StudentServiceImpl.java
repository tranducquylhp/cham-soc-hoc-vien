package com.codegym.demo_chatbot_fb.service.impl;

import com.codegym.demo_chatbot_fb.model.Student;
import com.codegym.demo_chatbot_fb.repository.StudentRepository;
import com.codegym.demo_chatbot_fb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            student.setCreateDate(new Date());
        } else {
            Student studentDb = studentRepository.findById(student.getId()).orElse(null);
            if (studentDb == null) return "Thông tin học viên không tồn tại";
            student.setCreateDate(studentDb.getCreateDate());
            student.setStatus(studentDb.isStatus());
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(df.parse(student.getDateRegister()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.MONTH, student.getMonthRegister().intValue());
        student.setDateExpired(df.format(c.getTime()));
        studentRepository.save(student);
        return "";
    }

    @Override
    public void delete(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setStatus(false);
        }
        studentRepository.save(student);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Iterable<Student> searchStudentByPhoneNumber(String phoneNumber) {
        return studentRepository.findAllByPhoneNumberContainingAndStatus(phoneNumber, true);
    }
}
