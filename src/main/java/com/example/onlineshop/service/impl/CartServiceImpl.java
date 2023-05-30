package com.example.onlineshop.service.impl;

import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.entity.Orders;
import com.example.onlineshop.repository.CartRepository;
import com.example.onlineshop.repository.OrdersRepository;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ashot Simonyan on 29.05.23.
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;


    @Override
    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public void remove(int id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> findById(Integer id) {
        return cartRepository.findById(id);
    }

    @Override
    public Optional<Cart> findByUserId(Integer id) {
        return cartRepository.findByUser_Id(id);
    }
}
