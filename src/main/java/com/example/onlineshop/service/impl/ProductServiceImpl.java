package com.example.onlineshop.service.impl;

import com.example.onlineshop.entity.Product;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ashot Simonyan on 29.05.23.
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Value("${shopping-app.upload.image.path}")
    private String imageUploadPath;

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void remove(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void save(Product product, MultipartFile multipartFile) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageUploadPath + fileName);
            multipartFile.transferTo(file);
            product.setImage(fileName);
        }
        productRepository.save(product);
    }

    @Override
    public Product findById(int id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return null;
    }

    @Override
    public List<Product> findAllByCategoryId(int id) {
        return productRepository.findAllByCategory_Id(id);
    }
}
