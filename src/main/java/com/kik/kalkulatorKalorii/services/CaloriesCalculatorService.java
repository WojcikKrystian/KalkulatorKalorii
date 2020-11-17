package com.kik.kalkulatorKalorii.services;

import com.kik.kalkulatorKalorii.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class CaloriesCalculatorService {
    private IngredientRepository ingredientRepository;

    public CaloriesCalculatorService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
        System.out.println(ingredientRepository.findByName("sugar"));
    }

}
