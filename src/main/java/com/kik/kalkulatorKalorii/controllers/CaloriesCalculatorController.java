package com.kik.kalkulatorKalorii.controllers;

import com.kik.kalkulatorKalorii.services.CaloriesCalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
