package com.kik.kalkulatorKalorii.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer proteins;
    private Integer carbs;
    private Integer fat;
    private Integer kcal;
    private BigDecimal amount;

    @ManyToOne
    private Dish dish;
}
