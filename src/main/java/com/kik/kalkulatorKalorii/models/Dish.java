package com.kik.kalkulatorKalorii.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.Set;

@Entity
@Data
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer proteinsTotal;
    private Integer carbsTotal;
    private Integer fatTotal;
    private Integer calTotal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dish")
    private Set<Ingredient> ingredients;
}
