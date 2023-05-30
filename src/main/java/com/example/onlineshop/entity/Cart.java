package com.example.onlineshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Created by Ashot Simonyan on 29.05.23.
 */

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

}
