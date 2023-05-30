package com.example.onlineshop.service;


import com.example.onlineshop.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {

    List<Cart> findAllCarts();

    void remove(int id);

    void save(Cart cart);

    Optional<Cart> findById(Integer id);
    Optional<Cart> findByUserId(Integer id);
}
