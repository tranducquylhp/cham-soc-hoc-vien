package com.codegym.demo_chatbot_fb.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean status;
    private String name;
    private String phoneNumber;
    private String parentPhoneNumber;
    private Date dateRegister;
    private Long monthRegister;
    private Date dateExpired;
    private Long classNumber;
    private String schedule;
    private Date createDate;
}
