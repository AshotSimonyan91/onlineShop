package com.example.onlineshop.service;


import com.example.onlineshop.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    void remove(int id);

    void save(User user);

    void removeById(int id);

    User findById(int id);

    Optional<User> findByEmail(String email);

}
