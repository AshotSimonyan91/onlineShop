package com.example.onlineshop.repository;


import com.example.onlineshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    Optional<Cart> findByUser_Id(int Id);

}
