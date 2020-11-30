package com.kik.kalkulatorKalorii.controllers;

import com.kik.kalkulatorKalorii.models.Ingredient;
import com.kik.kalkulatorKalorii.services.CaloriesCalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

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
        Set<Ingredient> results = service.getAllGeneralIngredients();
        model.addAttribute("ingredients", results);
        return "ingredients";
    }

    @GetMapping("/ingredients")
    public String getAllIngredients(Model model) {
        Set<Ingredient> results = service.getAllGeneralIngredients();
        model.addAttribute("ingredients", results);
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients";
    }

    @GetMapping("/dishes")
    public String getAllDishes(Model model) {
        return "dishes";
    }

}
