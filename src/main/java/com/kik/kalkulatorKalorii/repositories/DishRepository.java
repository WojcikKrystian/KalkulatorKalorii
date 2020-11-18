package com.kik.kalkulatorKalorii.repositories;

import com.kik.kalkulatorKalorii.models.Dish;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DishRepository extends CrudRepository<Dish, Long> {

    Optional<Dish> findByName(String name);
}
