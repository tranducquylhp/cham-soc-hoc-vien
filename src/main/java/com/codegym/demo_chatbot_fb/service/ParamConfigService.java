package com.codegym.demo_chatbot_fb.service;


import com.codegym.demo_chatbot_fb.model.ParamConfig;

import java.util.List;
import java.util.Optional;

public interface ParamConfigService {
    List<ParamConfig> findAll();
    String save(ParamConfig t);
    void delete(Long id);
    Optional<ParamConfig> findById(Long id);
}
