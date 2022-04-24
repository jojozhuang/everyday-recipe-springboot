package com.jojostudio.recipe;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Everyday Recipe API", version = "1.0", description = "Recipe Information"))
@SpringBootApplication
public class RecipeApplication {
  public static void main(String[] args) {
    SpringApplication.run(RecipeApplication.class, args);
  }
}