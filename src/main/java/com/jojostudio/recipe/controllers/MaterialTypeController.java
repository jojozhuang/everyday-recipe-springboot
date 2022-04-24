package com.jojostudio.recipe.controllers;

import com.jojostudio.recipe.entities.MaterialType;
import com.jojostudio.recipe.services.MaterialTypeService;
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
@RequestMapping("/api/materialtypes")
public class MaterialTypeController extends BaseController {
  @Autowired
  MaterialTypeService materialTypeService;

  // GET /materialtypes
  @Operation(summary = "Get all materialtypes", description = "Get all material types sorted by id",
      tags = { "MaterialType Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful retrieved materialtypes",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = MaterialType.class)))) })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<MaterialType> findAll() {
    return materialTypeService.findAll();
  }

  // GET /materialtypes/5
  @Operation(summary = "Get one material type", description = "Get one material type by id",
      tags = { "MaterialType Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful retrieved one material type by id",
        content = {@Content(schema = @Schema(implementation = MaterialType.class))}),
      @ApiResponse(responseCode = "404", description = "MaterialType is not found with the given id",
        content = @Content)})
  @GetMapping("/{id}")
  public ResponseEntity<MaterialType> findOne(@PathVariable(value = "id") long id) throws Exception {
    if (!materialTypeService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    MaterialType materialType = materialTypeService.findById(id);
    return ResponseEntity.ok().body(materialType);
  }

  // POST /materialtypes
  @Operation(summary = "Create new material type", description = "Create new material type",
      tags = { "MaterialType Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created one new materialtype",
        content = {@Content(schema = @Schema(implementation = MaterialType.class))})})
  @PostMapping("")
  public ResponseEntity<MaterialType> create(@Valid @RequestBody MaterialType materialType) {
    MaterialType newMaterialType = materialTypeService.create(materialType);
    return ResponseEntity.status(HttpStatus.CREATED).body(newMaterialType);
  }

  // PUT /materialtypes/5
  @Operation(summary = "Update one material type", description = "Update one materialtype",
      tags = { "MaterialType Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Update one material type by id",
        content = {@Content(schema = @Schema(implementation = MaterialType.class))}),
      @ApiResponse(responseCode = "404", description = "MaterialType is not found with the given id",
        content = @Content)})
  @PutMapping("/{id}")
  public ResponseEntity<MaterialType> update(@PathVariable(value = "id") Long id,
                                        @Valid @RequestBody MaterialType materialType) throws Exception {
    if (!materialTypeService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    MaterialType oldMaterialType = materialTypeService.findById(id);
    oldMaterialType.setTypeName(materialType.getTypeName());

    MaterialType updMaterialType = materialTypeService.update(oldMaterialType);
    return ResponseEntity.ok(updMaterialType);
  }

  // DELETE /materialtypes/5
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete one material type", description = "Delete one material type by id",
      tags = { "MaterialType Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully deleted material stype",
        content = {@Content(schema = @Schema(implementation = MaterialType.class))}),
      @ApiResponse(responseCode = "404", description = "MaterialType is not found with the given id",
        content = @Content)})
  public ResponseEntity<MaterialType> delete(@PathVariable(value = "id") long id) {
    if (!materialTypeService.exists(id)) {
      return ResponseEntity.notFound().build();
    }

    materialTypeService.delete(id);
    return ResponseEntity.ok().build();
  }
}
