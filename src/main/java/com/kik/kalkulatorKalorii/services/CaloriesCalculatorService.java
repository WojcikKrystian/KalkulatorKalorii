package com.kik.kalkulatorKalorii.services;

import com.kik.kalkulatorKalorii.models.Dish;
import com.kik.kalkulatorKalorii.models.Ingredient;
import com.kik.kalkulatorKalorii.repositories.DishRepository;
import com.kik.kalkulatorKalorii.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Ingredient> getGeneralIngredientByName(String name) {
        return ingredientRepository.findGeneralIngredientByName(name);
    }

    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public Long saveDish(Dish dish) {
        Dish savedDish = dishRepository.save(dish);
        return savedDish.getId();
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }

    public Optional<Ingredient> getIngredientById(Long id) {
        return ingredientRepository.findById(id);
    }

    public Optional<Dish> getDishById(Long id) {
        return dishRepository.findById(id);
    }

    public void updateIngredient(Long id, Ingredient updatedIngredient) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isPresent()) {
            ingredient.get().setName(updatedIngredient.getName());
            ingredient.get().setCarbs(updatedIngredient.getCarbs());
            ingredient.get().setFat(updatedIngredient.getFat());
            ingredient.get().setProteins(updatedIngredient.getProteins());
            ingredient.get().setKcal(updatedIngredient.getKcal());
            ingredientRepository.save(ingredient.get());
        }
    }

    public void updateDish(Long id, Dish updatedDish) {
        Optional<Dish> dishOptional = dishRepository.findById(id);
        dishOptional.ifPresent(dish -> {
            double proteinsTotal = 0.0;
            double carbsTotal = 0.0;
            double fatTotal = 0.0;
            double calTotal = 0.0;
            List<Ingredient> ingredients = new ArrayList<>();
            ingredientRepository.deleteIngredientByDish(dish);

            for (Ingredient ingredient : updatedDish.getIngredients()) {
                if(ingredient.getName() == null) {
                    // skip processing and continue with next
                    continue;
                }

                List<Ingredient> generalIngredients = ingredientRepository.findGeneralIngredientByName(ingredient.getName());
                Ingredient generalIngredient = generalIngredients.get(0);
                if (generalIngredient.getProteins() != null) {
                    proteinsTotal += generalIngredient.getProteins() * ingredient.getAmount() / 100;
                }
                if (generalIngredient.getCarbs() != null) {
                    carbsTotal += generalIngredient.getCarbs() * ingredient.getAmount() / 100;
                }
                if (generalIngredient.getFat() != null) {
                    fatTotal += generalIngredient.getFat() * ingredient.getAmount() / 100;
                }
                if (generalIngredient.getKcal() != null) {
                    calTotal += generalIngredient.getKcal() * ingredient.getAmount() / 100;
                }
                ingredient.setDish(dish);
                ingredients.add(ingredient);
            }

            dish.setName(updatedDish.getName());
            dish.setProteinsTotal(Math.round(proteinsTotal * 100.0) / 100.0);
            dish.setCalTotal(Math.round(calTotal * 100.0) / 100.0);
            dish.setCarbsTotal(Math.round(carbsTotal * 100.0) / 100.0);
            dish.setFatTotal(Math.round(fatTotal * 100.0) / 100.0);
            dish.setIngredients(ingredients);

            dishRepository.save(dish);
        });
    }
}
