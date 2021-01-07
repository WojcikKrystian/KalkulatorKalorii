package com.kik.kalkulatorKalorii.repositories;

import com.kik.kalkulatorKalorii.models.Dish;
import com.kik.kalkulatorKalorii.models.Ingredient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    @Query("SELECT DISTINCT ingredient FROM Ingredient ingredient WHERE ingredient.amount = 100 AND ingredient.dish is NULL")
    Set<Ingredient> findAllGeneralIngredients();

    @Query("SELECT DISTINCT ingredient.name FROM Ingredient ingredient WHERE ingredient.name like %:term%")
    Set<String> findAllNameSuggestions(String term);

    @Query(value = "SELECT ingredient FROM Ingredient ingredient WHERE ingredient.amount = 100 " +
            "AND ingredient.dish is NULL AND ingredient.name = :name")
    List<Ingredient> findGeneralIngredientByName(String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE from Ingredient ingredient WHERE ingredient.dish = :dish")
    void deleteIngredientByDish(Dish dish);
}
