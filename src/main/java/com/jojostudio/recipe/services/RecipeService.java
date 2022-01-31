package com.jojostudio.recipe.services;

import com.jojostudio.recipe.entities.Recipe;
import com.jojostudio.recipe.exception.NotFoundException;
import com.jojostudio.recipe.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
  @Autowired
  RecipeRepository recipeRepository;

  public List<Recipe> findAll() {
    return recipeRepository.findAll();
  }

  public Recipe findById(long id) throws NotFoundException {
    Optional<Recipe> recipe = recipeRepository.findById(id);

    if (recipe.isEmpty()) {
      throw new NotFoundException();
    }
    return recipe.get();
  }

  public Recipe create(Recipe recipe) {
    recipe.setId(0L);
    return recipeRepository.save(recipe);
  }

  public Recipe update(Recipe recipe) {
    return recipeRepository.save(recipe);
  }

  public void delete(long id) {
    recipeRepository.deleteById(id);
  }

  public boolean exists(long id) {
    return recipeRepository.existsById(id);
  }
}
