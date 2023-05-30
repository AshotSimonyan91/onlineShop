package com.example.onlineshop.controller;

import com.example.onlineshop.entity.*;
import com.example.onlineshop.security.CurrentUser;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.service.OrdersService;
import com.example.onlineshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;
    private final OrdersService ordersService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/forgot")
    public String forgotPassword() {
        return "password";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        Optional<User> byEmail = userService.findByEmail(user.getEmail());
        if (byEmail.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setUserType(UserType.USER);
            userService.save(user);
        }
        return "redirect:/";
    }



    @GetMapping("/admin")
    public String adminPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("currentUser", currentUser.getUser());
        return "admin";
    }

    @GetMapping("/order/{id}")
    public String orderPage(@PathVariable("id") int id,
                            @ModelAttribute Orders orders) {
        Optional<Cart> byId = cartService.findById(id);
        if (byId.isPresent()){
            Cart cart = byId.get();
            List<Product> products = cart.getProducts();
            orders.setDate(new Date());
            orders.setProducts(products);
            ordersService.save(orders);
            cartService.remove(id);
        }
        return "redirect:/order";
    }


}