package com.jojostudio.recipe.services;

import com.jojostudio.recipe.entities.RecipeCategory;
import com.jojostudio.recipe.exception.NotFoundException;
import com.jojostudio.recipe.repositories.RecipeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeCategoryService {
  @Autowired
  RecipeCategoryRepository recipeCategoryRepository;

  public List<RecipeCategory> findAll() {
    return recipeCategoryRepository.findAll();
  }

  public RecipeCategory findById(long id) throws NotFoundException {
    Optional<RecipeCategory> recipeCategory = recipeCategoryRepository.findById(id);

    if (recipeCategory.isEmpty()) {
      throw new NotFoundException();
    }
    return recipeCategory.get();
  }

  public RecipeCategory create(RecipeCategory recipeCategory) {
    recipeCategory.setId(0L);
    return recipeCategoryRepository.save(recipeCategory);
  }

  public RecipeCategory update(RecipeCategory recipeCategory) {
    return recipeCategoryRepository.save(recipeCategory);
  }

  public void delete(long id) {
    recipeCategoryRepository.deleteById(id);
  }

  public boolean exists(long id) {
    return recipeCategoryRepository.existsById(id);
  }
}
