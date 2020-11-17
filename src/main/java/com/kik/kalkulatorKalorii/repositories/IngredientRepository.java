package com.kik.kalkulatorKalorii.repositories;

import com.kik.kalkulatorKalorii.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Optional<Ingredient> findByName(String name);
}
