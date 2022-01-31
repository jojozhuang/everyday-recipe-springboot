package com.jojostudio.recipe.controllers;

import com.jojostudio.recipe.entities.Material;
import com.jojostudio.recipe.services.MaterialService;
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
@RequestMapping("/api/materials")
public class MaterialController extends BaseController {
  @Autowired
  MaterialService materialService;

  // GET /materials
  @Operation(summary = "Get all materials", description = "Get all materials sorted by id",
      tags = { "Material Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful retrieved materials",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = Material.class)))) })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Material> findAll() {
    return materialService.findAll();
  }

  // GET /materials/5
  @Operation(summary = "Get one material", description = "Get one material by id", tags = { "Material Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful retrieved one material by id",
        content = {@Content(schema = @Schema(implementation = Material.class))}),
      @ApiResponse(responseCode = "404", description = "Material is not found with the given id",
        content = @Content)})
  @GetMapping("/{id}")
  public ResponseEntity<Material> findOne(@PathVariable(value = "id") long id) throws Exception {
    if (!materialService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    Material material = materialService.findById(id);
    return ResponseEntity.ok().body(material);
  }

  // POST /materials
  @Operation(summary = "Create new material", description = "Create new material", tags = { "Material Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created one new material",
        content = {@Content(schema = @Schema(implementation = Material.class))})})
  @PostMapping("")
  public ResponseEntity<Material> create(@Valid @RequestBody Material material) {
    Material newMaterial = materialService.create(material);
    return ResponseEntity.status(HttpStatus.CREATED).body(newMaterial);
  }

  // PUT /materials/5
  @Operation(summary = "Update one material", description = "Update one material", tags = { "Material Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Update one material by id",
        content = {@Content(schema = @Schema(implementation = Material.class))}),
      @ApiResponse(responseCode = "404", description = "Material is not found with the given id",
        content = @Content)})
  @PutMapping("/{id}")
  public ResponseEntity<Material> update(@PathVariable(value = "id") Long id,
                                        @Valid @RequestBody Material material) throws Exception {
    if (!materialService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    Material oldMaterial = materialService.findById(id);
    oldMaterial.setMaterialType(material.getMaterialType());
    oldMaterial.setName(material.getName());
    oldMaterial.setDescription(material.getDescription());

    Material updMaterial = materialService.update(oldMaterial);
    return ResponseEntity.ok(updMaterial);
  }

  // DELETE /materials/5
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete one material", description = "Delete one material by id",
      tags = { "Material Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully deleted material",
        content = {@Content(schema = @Schema(implementation = Material.class))}),
      @ApiResponse(responseCode = "404", description = "Material is not found with the given id",
        content = @Content)})
  public ResponseEntity<Material> delete(@PathVariable(value = "id") long id) {
    if (!materialService.exists(id)) {
      return ResponseEntity.notFound().build();
    }

    materialService.delete(id);
    return ResponseEntity.ok().build();
  }
}
