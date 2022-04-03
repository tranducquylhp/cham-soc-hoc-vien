package com.codegym.demo_chatbot_fb.model;

import javax.persistence.*;

@Entity
@Table(name = "param_config")
public class ParamConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long value;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
