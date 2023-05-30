package com.example.onlineshop.service;


import com.example.onlineshop.entity.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> findAllOrder();

    void remove(int id);

    void save(Orders order);
}
