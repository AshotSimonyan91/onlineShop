package com.example.onlineshop.controller;

import com.example.onlineshop.entity.Cart;
import com.example.onlineshop.entity.User;
import com.example.onlineshop.entity.UserType;
import com.example.onlineshop.security.CurrentUser;
import com.example.onlineshop.service.CartService;
import com.example.onlineshop.service.CategoryService;
import com.example.onlineshop.service.MainService;
import com.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CartService cartService;

    @GetMapping("/")
    private String main(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            Optional<Cart> byUserId = cartService.findByUserId(currentUser.getUser().getId());
            if (byUserId.isPresent()) {
                modelMap.addAttribute("currentCart", byUserId.get());
            }
        }
        modelMap.addAttribute("products", productService.findAllProducts());
        modelMap.addAttribute("categories", categoryService.findAllCategory());
        return "index";
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("profilePic") String imageName) throws IOException {
        return mainService.getImage(imageName);
    }

    @GetMapping("/customLogin")
    public String customLogin() {
        return "customLoginPage";
    }


    @GetMapping("/customSuccessLogin")
    public String customSuccessLogin(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getUserType() == UserType.ADMIN) {
                return "redirect:/user/admin";
            } else if (user.getUserType() == UserType.USER) {
                return "redirect:/";
            }
        }
        return "redirect:/";
    }
}