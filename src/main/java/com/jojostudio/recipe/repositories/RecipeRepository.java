package com.jojostudio.recipe.repositories;

import com.jojostudio.recipe.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> { }
