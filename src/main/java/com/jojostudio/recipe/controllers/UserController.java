package com.jojostudio.recipe.controllers;

import com.jojostudio.recipe.entities.User;
import com.jojostudio.recipe.services.UserService;
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
@RequestMapping("/api/users")
public class UserController extends BaseController {
  @Autowired
  UserService userService;

  // GET /users
  @Operation(summary = "Get all users", description = "Get all users sorted by id",
      tags = { "User Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful retrieved users",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))) })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<User> findAll() {
    return userService.findAll();
  }

  // GET /users/5
  @Operation(summary = "Get one user", description = "Get one user by id", tags = { "User Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful retrieved one user by id",
        content = {@Content(schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "404", description = "User is not found with the given id",
        content = @Content)})
  @GetMapping("/{id}")
  public ResponseEntity<User> findOne(@PathVariable(value = "id") long id) throws Exception {
    if (!userService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    User user = userService.findById(id);
    return ResponseEntity.ok().body(user);
  }

  // POST /users
  @Operation(summary = "Create new user", description = "Create new user", tags = { "User Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created one new user",
        content = {@Content(schema = @Schema(implementation = User.class))})})
  @PostMapping("")
  public ResponseEntity<User> create(@Valid @RequestBody User user) {
    User newUser = userService.create(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }

  // PUT /users/5
  @Operation(summary = "Update one user", description = "Update one user", tags = { "User Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Update one user by id",
        content = {@Content(schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "404", description = "User is not found with the given id",
        content = @Content)})
  @PutMapping("/{id}")
  public ResponseEntity<User> update(@PathVariable(value = "id") Long id,
                                        @Valid @RequestBody User user) throws Exception {
    if (!userService.exists(id)) {
      return ResponseEntity.notFound().build();
    }
    User oldUser = userService.findById(id);
    oldUser.setUsername(user.getUsername());
    oldUser.setEmail(user.getEmail());

    User updUser = userService.update(oldUser);
    return ResponseEntity.ok(updUser);
  }

  // DELETE /users/5
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete one user", description = "Delete one user by id",
      tags = { "User Controller" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully deleted user",
        content = {@Content(schema = @Schema(implementation = User.class))}),
      @ApiResponse(responseCode = "404", description = "User is not found with the given id",
        content = @Content)})
  public ResponseEntity<User> delete(@PathVariable(value = "id") long id) {
    if (!userService.exists(id)) {
      return ResponseEntity.notFound().build();
    }

    userService.delete(id);
    return ResponseEntity.ok().build();
  }
}
