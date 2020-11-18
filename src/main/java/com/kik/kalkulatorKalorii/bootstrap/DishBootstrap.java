package com.kik.kalkulatorKalorii.bootstrap;

import com.kik.kalkulatorKalorii.models.Dish;
import com.kik.kalkulatorKalorii.models.Ingredient;
import com.kik.kalkulatorKalorii.repositories.DishRepository;
import com.kik.kalkulatorKalorii.repositories.IngredientRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

//@Component
public class DishBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final DishRepository dishRepository;

    public DishBootstrap(DishRepository dishRepository, IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Ingredient ingredient1 = Ingredient.builder()
                .name("potato")
                .amount(20.0)
                .carbs(10.0)
                .fat(2.0)
                .proteins(4.0)
                .kcal(50.0)
                .build();

        Ingredient ingredient2 = Ingredient.builder()
                .name("onion")
                .amount(5.0)
                .carbs(3.0)
                .fat(0.3)
                .proteins(1.2)
                .kcal(10.0)
                .build();

        Dish testDish = new Dish("testDish");
        testDish.addIngredient(ingredient1);
        testDish.addIngredient(ingredient2);

        dishRepository.save(testDish);
    }
}
