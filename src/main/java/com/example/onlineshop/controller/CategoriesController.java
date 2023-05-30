package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Category;
import com.example.onlineshop.service.CategoryService;
import com.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/add")
    public String addCategoryPage() {
        return "addCategory";
    }

    @PostMapping("/categories/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/user/admin";
    }

    @GetMapping("/products/{id}")
    public String currentProductPage(ModelMap modelmap, @PathVariable("id") int id) {
        modelmap.addAttribute("products", productService.findAllByCategoryId(id));
        modelmap.addAttribute("categories", categoryService.findAllCategory());
        return "productsByCategory";
    }


}
