package com.example.onlineshop.service;


import com.example.onlineshop.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAllCategory();

    void remove(int id);

    void save(Category category);

    Optional<Category> findById(Integer id);
}
