package com.kik.kalkulatorKalorii.repositories;

import com.kik.kalkulatorKalorii.models.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Optional<Ingredient> findByName(String name);

    @Query("SELECT DISTINCT ingredient FROM Ingredient ingredient WHERE ingredient.amount = 100 AND ingredient.dish is NULL")
    Set<Ingredient> findAllGeneralIngredients();
}
