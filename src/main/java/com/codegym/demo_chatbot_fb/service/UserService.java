package com.codegym.demo_chatbot_fb.service;

import com.codegym.demo_chatbot_fb.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService extends GeneralService<User,String>{

    Iterable<User> findAllByStatusIsTrue();

    Iterable<User> findAllByStatusIsFalse();
}
