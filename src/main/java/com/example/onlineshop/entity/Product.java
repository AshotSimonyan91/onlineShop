package com.example.onlineshop.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Created by Ashot Simonyan on 29.05.23.
 */

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private Double price;
    private String description;
    private String image;

    @ManyToOne(optional = false)
    private Category category;
}
