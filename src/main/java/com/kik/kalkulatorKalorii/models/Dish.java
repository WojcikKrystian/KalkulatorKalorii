package com.kik.kalkulatorKalorii.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double proteinsTotal = 0.0;
    private Double carbsTotal = 0.0;
    private Double fatTotal = 0.0;
    private Double calTotal = 0.0;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dish")
    private List<Ingredient> ingredients = new ArrayList<>();

    public Dish(String name) {
        this.name = name;
    }

    public Dish addIngredient(Ingredient ingredient) {
        ingredient.setDish(this);
        this.ingredients.add(ingredient);
        this.proteinsTotal += ingredient.getProteins();
        this.carbsTotal += ingredient.getCarbs();
        this.fatTotal += ingredient.getFat();
        this.calTotal += ingredient.getKcal();
        return this;
    }

}

