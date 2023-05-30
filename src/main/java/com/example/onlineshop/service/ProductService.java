package com.example.onlineshop.service;


import com.example.onlineshop.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    void remove(int id);

    void save(Product product);

    void save(Product product, MultipartFile multipartFile) throws IOException;

    Product findById(int id);

    List<Product> findAllByCategoryId(int id);
}
