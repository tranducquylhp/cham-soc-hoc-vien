package com.codegym.demo_chatbot_fb.repository;

import com.codegym.demo_chatbot_fb.model.ParamConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParamConfigRepository extends JpaRepository<ParamConfig, Long> {
    @Query("from ParamConfig p order by p.value desc ")
    List<ParamConfig> findAllOrderValue();
}
