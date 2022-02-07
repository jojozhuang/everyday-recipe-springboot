package com.jojostudio.recipe.repositories;

import com.jojostudio.recipe.entities.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Long> { }
