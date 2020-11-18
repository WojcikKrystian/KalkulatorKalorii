package com.kik.kalkulatorKalorii.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
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
    private Set<Ingredient> ingredients = new HashSet<>();

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

