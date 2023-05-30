package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.entity.Product;
import com.example.onlineshop.security.CurrentUser;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/products/{id}")
    public String cartPage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Cart> byId = cartService.findById(id);
        if (byId.isPresent()) {
            modelMap.addAttribute("cart", byId.get());
        }
        return "cart";
    }

    @GetMapping("{id}")
    public String addCartPage(ModelMap modelmap, @PathVariable("id") int id,
                              @AuthenticationPrincipal CurrentUser currentUser, @RequestParam("count") int count) {
        Optional<Cart> byUserId = cartService.findByUserId(currentUser.getUser().getId());
        if (byUserId.isEmpty()) {
            List<Product> products = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                products.add(productService.findById(id));
            }
            Cart cart = new Cart();
            cart.setUser(currentUser.getUser());
            cart.setProducts(products);
            cartService.save(cart);
        } else {
            Cart cart = byUserId.get();
            List<Product> products1 = cart.getProducts();
            for (int i = 0; i < count; i++) {
                products1.add(productService.findById(id));
            }
            cart.setUser(currentUser.getUser());
            cart.setProducts(products1);
            cartService.save(cart);
        }
        return "redirect:/products/" + id;
    }


    @GetMapping("/remove")
    public String removeProductInCart(@RequestParam("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        int id1 = 0;
        Optional<Cart> byUserId = cartService.findByUserId(currentUser.getUser().getId());
        if (byUserId.isPresent()) {
            List<Product> products = byUserId.get().getProducts();
            for (Product product : products) {
                if (product.getId() == id) {
                    id1 = product.getId();
                }
            }
            products.remove(productService.findById(id1));
            Cart cart = byUserId.get();
            cart.setUser(currentUser.getUser());
            cart.setProducts(products);
            cartService.save(cart);
        }
        return "redirect:/cart/products/" + byUserId.get().getId();
    }

}
