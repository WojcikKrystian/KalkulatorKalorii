package com.kik.kalkulatorKalorii.services;

import com.kik.kalkulatorKalorii.models.Dish;
import com.kik.kalkulatorKalorii.models.Ingredient;
import com.kik.kalkulatorKalorii.repositories.DishRepository;
import com.kik.kalkulatorKalorii.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CaloriesCalculatorService {
    private IngredientRepository ingredientRepository;
    private DishRepository dishRepository;

    public CaloriesCalculatorService(IngredientRepository ingredientRepository, DishRepository dishRepository) {
        this.ingredientRepository = ingredientRepository;
        this.dishRepository = dishRepository;
    }

    public Set<Ingredient> getAllGeneralIngredients() {
        return ingredientRepository.findAllGeneralIngredients();
    }

    public Set<Dish> getAllDishes() {
        Set<Dish> allDishes = new HashSet<>();
        dishRepository.findAll().forEach(allDishes::add);
        return allDishes;
    }

    public Set<String> getSuggestedNames(String term) {
        return new HashSet<>(ingredientRepository.findAllNameSuggestions(term));
    }

    public Optional<Ingredient> getGeneralIngredientByName(String name) {
        return Optional.ofNullable(ingredientRepository.findGeneralIngredientByName(name));
    }

    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public void saveDish(Dish dish) {
        dishRepository.save(dish);
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}
