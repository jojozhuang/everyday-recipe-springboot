package com.jojostudio.recipe.controllers;

import com.jojostudio.recipe.entities.Recipe;
import com.jojostudio.recipe.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController extends BaseController {
  @Autowired
  RecipeService recipeService;

  // GET /recipes
  @Operation(summary = "Get all recipes", description = "Get all recipes sorted by id",
      tags = { "Recipe Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful retrieved recipes",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = Recipe.class)))) })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Recipe> findAll() {
    return recipeService.findAll();
  }

  // GET /recipes/5
  @Operation(summary = "Get one recipe", description = "Get one recipe by id", tags = { "Recipe Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful retrieved one recipe by id",
        content = {@Content(schema = @Schema(implementation = Recipe.class))}),
      @ApiResponse(responseCode = "404", description = "Recipe is not found with the given id",
        content = @Content)})
  @GetMapping("/{id}")
  public ResponseEntity<Recipe> findOne(@PathVariable(value = "id") long id) throws Exception {
    if (!recipeService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    Recipe recipe = recipeService.findById(id);
    return ResponseEntity.ok().body(recipe);
  }

  // POST /recipes
  @Operation(summary = "Create new recipe", description = "Create new recipe", tags = { "Recipe Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created one new recipe",
        content = {@Content(schema = @Schema(implementation = Recipe.class))})})
  @PostMapping("")
  public ResponseEntity<Recipe> create(@Valid @RequestBody Recipe recipe) {
    Recipe newRecipe = recipeService.create(recipe);
    return ResponseEntity.status(HttpStatus.CREATED).body(newRecipe);
  }

  // PUT /recipes/5
  @Operation(summary = "Update one recipe", description = "Update one recipe", tags = { "Recipe Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Update one recipe by id",
        content = {@Content(schema = @Schema(implementation = Recipe.class))}),
      @ApiResponse(responseCode = "404", description = "Recipe is not found with the given id",
        content = @Content)})
  @PutMapping("/{id}")
  public ResponseEntity<Recipe> update(@PathVariable(value = "id") Long id,
                                        @Valid @RequestBody Recipe recipe) throws Exception {
    if (!recipeService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    Recipe oldRecipe = recipeService.findById(id);
    oldRecipe.setRecipeCategory(recipe.getRecipeCategory());
    oldRecipe.setRecipeName(recipe.getRecipeName());
    oldRecipe.setMaterials(recipe.getMaterials());
    oldRecipe.setSeasonings(recipe.getSeasonings());

    Recipe updRecipe = recipeService.update(oldRecipe);
    return ResponseEntity.ok(updRecipe);
  }

  // DELETE /recipes/5
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete one recipe", description = "Delete one recipe by id", tags = { "Recipe Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully deleted recipe",
        content = {@Content(schema = @Schema(implementation = Recipe.class))}),
      @ApiResponse(responseCode = "404", description = "Recipe is not found with the given id",
        content = @Content)})
  public ResponseEntity<Recipe> delete(@PathVariable(value = "id") long id) {
    if (!recipeService.exists(id)) {
      return ResponseEntity.notFound().build();
    }

    recipeService.delete(id);
    return ResponseEntity.ok().build();
  }
}
