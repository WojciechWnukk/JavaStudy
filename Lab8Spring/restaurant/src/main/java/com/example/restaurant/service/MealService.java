package com.example.restaurant.service;

import com.example.restaurant.model.Meal;
import com.example.restaurant.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {
    private final MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public Meal getMealById(Long id) {
        return mealRepository.findById(id).orElse(null);
    }

    public Meal addMeal(Meal meal) {
        return mealRepository.save(meal);
    }
    public Meal updateMeal(Long id, Meal updatedMeal) {
        Optional<Meal> existingMeal = mealRepository.findById(id);
        if (existingMeal.isPresent()) {
            Meal meal = existingMeal.get();
            meal.setMealName(updatedMeal.getMealName());
            meal.setPrice(updatedMeal.getPrice());
            // Możesz dodać inne pola, które chcesz zaktualizować
            return mealRepository.save(meal);
        } else {
            // Obsłuż błąd - np. rzucenie wyjątku lub zwrócenie wartości domyślnej
            return null;
        }
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }
}

