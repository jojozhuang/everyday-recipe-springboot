package com.jojostudio.recipe.repositories;

import com.jojostudio.recipe.entities.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Long> { }
