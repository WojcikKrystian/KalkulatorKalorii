package com.kik.kalkulatorKalorii.services;

import com.kik.kalkulatorKalorii.models.Ingredient;
import com.kik.kalkulatorKalorii.repositories.IngredientRepository;
import lombok.extern.log4j.Log4j2;
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

    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

}
