package com.kik.kalkulatorKalorii.services;

import com.kik.kalkulatorKalorii.models.Ingredient;
import com.kik.kalkulatorKalorii.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CaloriesCalculatorService {
    private IngredientRepository ingredientRepository;

    public CaloriesCalculatorService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Set<Ingredient> getAllGeneralIngredients() {
        return ingredientRepository.findAllGeneralIngredients();
    }

}
