package com.example.onlineshop.controller;

import com.example.onlineshop.entity.User;
import com.example.onlineshop.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CurrentUserControllerAdvice {

    @ModelAttribute("currentUser")
    public User curretnUser(@AuthenticationPrincipal CurrentUser currentUser){
        if(currentUser != null){
            return currentUser.getUser();
        }
        return null;
    }
}