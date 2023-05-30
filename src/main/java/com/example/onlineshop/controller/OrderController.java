package com.example.onlineshop.controller;

import com.example.onlineshop.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrdersService orderService;


    @GetMapping
    public String orderPage(ModelMap modelMap) {
        modelMap.addAttribute("orders",orderService.findAllOrder());
        return "orders";
    }


    @GetMapping("/remove")
    public String removeOrder(@RequestParam("id") int id) {
        orderService.remove(id);
        return "redirect:/order";
    }
}
