package com.example.onlineshop.service.impl;

import com.example.onlineshop.entity.Orders;
import com.example.onlineshop.repository.OrdersRepository;
import com.example.onlineshop.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ashot Simonyan on 29.05.23.
 */
@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;


    @Override
    public List<Orders> findAllOrder() {
        return ordersRepository.findAll();
    }

    @Override
    public void remove(int id) {
        ordersRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(Orders order) {

        ordersRepository.save(order);
    }
}
