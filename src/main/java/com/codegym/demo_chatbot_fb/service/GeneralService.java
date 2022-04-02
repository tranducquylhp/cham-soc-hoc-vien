package com.codegym.demo_chatbot_fb.service;

import java.util.Optional;

public interface GeneralService<T, E> {
    Iterable<T> findAll();
    void save(T t);
    void delete(E id);

    Optional<T> findById(E id);
}
