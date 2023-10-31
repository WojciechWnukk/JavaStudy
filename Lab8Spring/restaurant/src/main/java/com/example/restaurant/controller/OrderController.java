package com.example.restaurant.controller;

import com.example.restaurant.model.Meal;
import com.example.restaurant.model.Order;
import com.example.restaurant.service.MealService;
import com.example.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private MealService mealService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
/*
    @GetMapping("/meal/{mealId}")
    public List<Order> getOrdersByMeal(@PathVariable Long mealId) {
        Meal meal = mealService.getMealById(mealId);
        return orderService.getOrdersByMeal(meal);
    }
*/
}
