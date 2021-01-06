package com.kik.kalkulatorKalorii.controllers;

import com.kik.kalkulatorKalorii.models.Dish;
import com.kik.kalkulatorKalorii.models.Ingredient;
import com.kik.kalkulatorKalorii.services.CaloriesCalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CaloriesCalculatorController {

    private CaloriesCalculatorService service;

    public CaloriesCalculatorController(CaloriesCalculatorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getMainPage() {
        return "index";
    }

    @PostMapping("/ingredients")
    public String addIngredient(@ModelAttribute Ingredient ingredient, Model model) {
        ingredient.setAmount(100.0);
        service.saveIngredient(ingredient);

        Set<Ingredient> ingredients = service.getAllGeneralIngredients()
                .stream()
                .sorted(Comparator.comparing(Ingredient::getId).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        model.addAttribute("ingredients", ingredients);
        return "ingredients";
    }

    @GetMapping("/ingredients")
    public String getAllIngredients(Model model) {
        Set<Ingredient> ingredients = service.getAllGeneralIngredients()
                .stream()
                .sorted(Comparator.comparing(Ingredient::getId).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        model.addAttribute("ingredients", ingredients);
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients";
    }

    @GetMapping("/dishes")
    public String getAllDishes(Model model) {
        Set<Dish> dishes = service.getAllDishes()
                .stream()
                .sorted(Comparator.comparing(Dish::getId).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        model.addAttribute("dishes", dishes);
        return "dishes";
    }

    @GetMapping("/dishes/new")
    public String getAddNewDishForm(Model model) {
        List<Ingredient> results = new ArrayList<>();
        Ingredient temp = Ingredient.builder().name("").amount(null).build();
        results.add(temp);
        Dish newDish = new Dish();
        newDish.setIngredients(results);
        model.addAttribute("dish", newDish);
        model.addAttribute("generalIngredients", service.getAllGeneralIngredients());
        return "new-dish";
    }

    @PostMapping("/dishes/new")
    public String addIngredientToNewDishForm(@ModelAttribute Dish dish, Model model) {
        double proteinsTotal = 0.0;
        double carbsTotal = 0.0;
        double fatTotal = 0.0;
        double calTotal = 0.0;

        for(Ingredient ingredient : dish.getIngredients()) {
            Optional<Ingredient> generalIngredientOptional = service.getGeneralIngredientByName(ingredient.getName());

            if(generalIngredientOptional.isPresent()) {
                Ingredient generalIngredient = generalIngredientOptional.get();
                if(generalIngredient.getProteins() != null) {
                    proteinsTotal += generalIngredient.getProteins() * ingredient.getAmount() / 100;
                }
                if(generalIngredient.getCarbs() != null) {
                    carbsTotal += generalIngredient.getCarbs() * ingredient.getAmount() / 100;
                }
                if(generalIngredient.getFat() != null) {
                    fatTotal += generalIngredient.getFat() * ingredient.getAmount() / 100;
                }
                if(generalIngredient.getKcal() != null) {
                    calTotal += generalIngredient.getKcal() * ingredient.getAmount() / 100;
                }
            }
        }

        Dish newDish = Dish.builder()
                .name(dish.getName())
                .proteinsTotal(proteinsTotal)
                .calTotal(calTotal)
                .carbsTotal(carbsTotal)
                .fatTotal(fatTotal)
                .ingredients(dish.getIngredients())
                .build();

        service.saveDish(newDish);
        return "redirect:/dishes";
    }

    @PostMapping("/ingredients/delete/{ingredientId}")
    public String deleteIngredient(@PathVariable Long ingredientId) {
        service.deleteIngredient(ingredientId);
        return "redirect:/ingredients";
    }
}
