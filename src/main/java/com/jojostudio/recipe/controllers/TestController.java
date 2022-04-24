package com.jojostudio.recipe.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = TestController.MAX_AGE)
@RestController
@RequestMapping("/api/test")
public class TestController {
  public static final int MAX_AGE = 3600;

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/starter")
  @PreAuthorize("hasRole('STARTER') or hasRole('PAID') or hasRole('ADMIN')")
  public String starterAccess() {
    return "Starter Content.";
  }

  @GetMapping("/paid")
  @PreAuthorize("hasRole('PAID') or hasRole('ADMIN')")
  public String paidAccess() {
    return "Paid Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
