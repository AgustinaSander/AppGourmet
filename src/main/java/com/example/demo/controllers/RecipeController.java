package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Recipe;
import com.example.demo.repositories.RecipeRepository;

@RestController
public class RecipeController {
	private final RecipeRepository recipeRepository;
	
	RecipeController (RecipeRepository recipeRepository){
		this.recipeRepository = recipeRepository;
	}
	
	@GetMapping("/recipes")
   	List<Recipe> all() {
          	List<Recipe> recipes = new ArrayList<>();
          	recipeRepository.findAll().forEach(recipes::add);
          	return recipes;
   	}
}
