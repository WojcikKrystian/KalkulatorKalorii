package com.kik.kalkulatorKalorii.controllers;

import com.kik.kalkulatorKalorii.models.Ingredient;
import com.kik.kalkulatorKalorii.services.CaloriesCalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/ingredients")
    public String getAllIngredients(Model model) {
        Set<Ingredient> results = service.getAllGeneralIngredients();
        model.addAttribute("ingredients", results);
        return "ingredients";
    }

    @GetMapping("/dishes")
    public String getAllDishes(Model model) {
        return "dishes";
    }

}
