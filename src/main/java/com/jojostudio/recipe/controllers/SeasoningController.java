package com.jojostudio.recipe.controllers;

import com.jojostudio.recipe.entities.Seasoning;
import com.jojostudio.recipe.services.SeasoningService;
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
@RequestMapping("/api/seasonings")
public class SeasoningController extends BaseController {
  @Autowired
  SeasoningService seasoningService;

  // GET /seasonings
  @Operation(summary = "Get all seasonings", description = "Get all seasonings sorted by id",
      tags = { "Seasoning Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful retrieved seasonings",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = Seasoning.class)))) })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Seasoning> findAll() {
    return seasoningService.findAll();
  }

  // GET /seasonings/5
  @Operation(summary = "Get one seasoning", description = "Get one seasoning by id", tags = { "Seasoning Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful retrieved one seasoning by id",
        content = {@Content(schema = @Schema(implementation = Seasoning.class))}),
      @ApiResponse(responseCode = "404", description = "Seasoning is not found with the given id",
        content = @Content)})
  @GetMapping("/{id}")
  public ResponseEntity<Seasoning> findOne(@PathVariable(value = "id") long id) throws Exception {
    if (!seasoningService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    Seasoning seasoning = seasoningService.findById(id);
    return ResponseEntity.ok().body(seasoning);
  }

  // POST /seasonings
  @Operation(summary = "Create new seasoning", description = "Create new seasoning", tags = { "Seasoning Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created one new seasoning",
        content = {@Content(schema = @Schema(implementation = Seasoning.class))})})
  @PostMapping("")
  public ResponseEntity<Seasoning> create(@Valid @RequestBody Seasoning seasoning) {
    Seasoning newSeasoning = seasoningService.create(seasoning);
    return ResponseEntity.status(HttpStatus.CREATED).body(newSeasoning);
  }

  // PUT /seasonings/5
  @Operation(summary = "Update one seasoning", description = "Update one seasoning", tags = { "Seasoning Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Update one seasoning by id",
        content = {@Content(schema = @Schema(implementation = Seasoning.class))}),
      @ApiResponse(responseCode = "404", description = "Seasoning is not found with the given id",
        content = @Content)})
  @PutMapping("/{id}")
  public ResponseEntity<Seasoning> update(@PathVariable(value = "id") Long id,
                                        @Valid @RequestBody Seasoning seasoning) throws Exception {
    if (!seasoningService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    Seasoning oldSeasoning = seasoningService.findById(id);
    oldSeasoning.setSeasoningName(seasoning.getSeasoningName());

    Seasoning updSeasoning = seasoningService.update(oldSeasoning);
    return ResponseEntity.ok(updSeasoning);
  }

  // DELETE /seasonings/5
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete one seasoning", description = "Delete one seasoning by id",
      tags = { "Seasoning Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully deleted seasoning",
        content = {@Content(schema = @Schema(implementation = Seasoning.class))}),
      @ApiResponse(responseCode = "404", description = "Seasoning is not found with the given id",
        content = @Content)})
  public ResponseEntity<Seasoning> delete(@PathVariable(value = "id") long id) {
    if (!seasoningService.exists(id)) {
      return ResponseEntity.notFound().build();
    }

    seasoningService.delete(id);
    return ResponseEntity.ok().build();
  }
}
