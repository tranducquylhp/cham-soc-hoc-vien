package com.codegym.demo_chatbot_fb.repository;

import com.codegym.demo_chatbot_fb.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("from Student s where s.status = true")
    Iterable<Student> findAllActive();

    List<Student> findAllByPhoneNumberContainingAndStatus(String phoneNumber, boolean status);
}
