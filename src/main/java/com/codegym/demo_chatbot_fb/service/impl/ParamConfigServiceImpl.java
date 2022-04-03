package com.codegym.demo_chatbot_fb.service.impl;

import com.codegym.demo_chatbot_fb.model.ParamConfig;
import com.codegym.demo_chatbot_fb.repository.ParamConfigRepository;
import com.codegym.demo_chatbot_fb.service.ParamConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ParamConfigServiceImpl implements ParamConfigService {

    @Autowired
    private ParamConfigRepository paramConfigRepository;

    @Override
    public List<ParamConfig> findAll() {
        return paramConfigRepository.findAllOrderValue();
    }

    @Override
    public String save(ParamConfig t) {
        if (!StringUtils.hasText(t.getName())
                || !StringUtils.hasText(t.getDescription())
                || t.getValue() == null) return "Bạn chưa điền các thông tin bắt buộc";
        paramConfigRepository.save(t);
        return "";
    }

    @Override
    public void delete(Long id) {
        paramConfigRepository.deleteById(id);
    }

    @Override
    public Optional<ParamConfig> findById(Long id) {
        return paramConfigRepository.findById(id);
    }
}
