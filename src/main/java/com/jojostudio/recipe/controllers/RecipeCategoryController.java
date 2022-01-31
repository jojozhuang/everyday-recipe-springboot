package com.jojostudio.recipe.controllers;

import com.jojostudio.recipe.entities.RecipeCategory;
import com.jojostudio.recipe.services.RecipeCategoryService;
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
@RequestMapping("/api/recipecategories")
public class RecipeCategoryController extends BaseController {
  @Autowired
  RecipeCategoryService recipeCategoryService;

  // GET /recipecategories
  @Operation(summary = "Get all recipe categories", description = "Get all recipe categories sorted by id",
      tags = { "RecipeCategory Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful retrieved recipe categories",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = RecipeCategory.class)))) })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<RecipeCategory> findAll() {
    return recipeCategoryService.findAll();
  }

  // GET /recipecategories/5
  @Operation(summary = "Get one recipe category", description = "Get one recipe category by id",
      tags = { "RecipeCategory Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful retrieved one recipe category by id",
        content = {@Content(schema = @Schema(implementation = RecipeCategory.class))}),
      @ApiResponse(responseCode = "404", description = "RecipeCategory is not found with the given id",
        content = @Content)})
  @GetMapping("/{id}")
  public ResponseEntity<RecipeCategory> findOne(@PathVariable(value = "id") long id) throws Exception {
    if (!recipeCategoryService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    RecipeCategory recipecategory = recipeCategoryService.findById(id);
    return ResponseEntity.ok().body(recipecategory);
  }

  // POST /recipecategories
  @Operation(summary = "Create new recipe category", description = "Create new recipe category",
      tags = { "RecipeCategory Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created one new recipe category",
        content = {@Content(schema = @Schema(implementation = RecipeCategory.class))})})
  @PostMapping("")
  public ResponseEntity<RecipeCategory> create(@Valid @RequestBody RecipeCategory recipecategory) {
    RecipeCategory newRecipeCategory = recipeCategoryService.create(recipecategory);
    return ResponseEntity.status(HttpStatus.CREATED).body(newRecipeCategory);
  }

  // PUT /recipecategories/5
  @Operation(summary = "Update one recipecategory", description = "Update one recipecategory",
      tags = { "RecipeCategory Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Update one recipecategory by id",
        content = {@Content(schema = @Schema(implementation = RecipeCategory.class))}),
      @ApiResponse(responseCode = "404", description = "RecipeCategory is not found with the given id",
        content = @Content)})
  @PutMapping("/{id}")
  public ResponseEntity<RecipeCategory> update(@PathVariable(value = "id") Long id,
                                        @Valid @RequestBody RecipeCategory recipecategory) throws Exception {
    if (!recipeCategoryService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    RecipeCategory oldRecipeCategory = recipeCategoryService.findById(id);
    oldRecipeCategory.setCategoryName(recipecategory.getCategoryName());

    RecipeCategory updRecipeCategory = recipeCategoryService.update(oldRecipeCategory);
    return ResponseEntity.ok(updRecipeCategory);
  }

  // DELETE /recipecategories/5
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete one recipe category", description = "Delete one recipe category by id",
      tags = { "RecipeCategory Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully deleted recipe category",
        content = {@Content(schema = @Schema(implementation = RecipeCategory.class))}),
      @ApiResponse(responseCode = "404", description = "RecipeCategory is not found with the given id",
        content = @Content)})
  public ResponseEntity<RecipeCategory> delete(@PathVariable(value = "id") long id) {
    if (!recipeCategoryService.exists(id)) {
      return ResponseEntity.notFound().build();
    }

    recipeCategoryService.delete(id);
    return ResponseEntity.ok().build();
  }
}
