package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.security.CurrentUser;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.service.CategoryService;
import com.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CartService cartService;

    @GetMapping()
    public String productPage(ModelMap modelmap) {
        modelmap.addAttribute("products", productService.findAllProducts());
        return "allProducts";
    }


    @GetMapping("{id}")
    public String currentProductPage(ModelMap modelmap, @PathVariable("id") int id,
                                     @AuthenticationPrincipal CurrentUser currentUser) {
        modelmap.addAttribute("product", productService.findById(id));
        modelmap.addAttribute("products", productService.findAllProducts());
        Optional<Cart> byUserId = cartService.findByUserId(currentUser.getUser().getId());
        if (byUserId.isPresent()) {
            Cart cart = byUserId.get();
            modelmap.addAttribute("currentCart", cart);
        }
        return "singlePage";
    }

    @GetMapping("/add")
    public String addProductPage(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAllCategory());
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("profile_pic") MultipartFile multipartFile) throws IOException {
        productService.save(product, multipartFile);
        return "redirect:/";
    }
}
