package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.FoodQuantity;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.DTO.FoodDTO;
import com.example.demo.domain.DTO.RecipeDTO;
import com.example.demo.repositories.RecipeRepository;

@RestController
public class RecipeController {
	private final RecipeRepository recipeRepository;
	
	RecipeController (RecipeRepository recipeRepository){
		this.recipeRepository = recipeRepository;
	}
	
	@GetMapping("/recipes")
   	List<RecipeDTO> all() {
		List<RecipeDTO> recipeDTO = new ArrayList<>();
        ((List<Recipe>) recipeRepository.findAll()).stream()
         										.forEach(recipe -> recipeDTO.add(recipe.convertToRecipeDTO()));
        return recipeDTO;
   	}
	

	@GetMapping("/recipes/{id}/foods")
   	List<FoodDTO> foodsInRecipe(@PathVariable int id) {
		List<FoodDTO> foodDTO = new ArrayList<>();
		List<FoodQuantity> foodQuantities = recipeRepository.findById(id).get().getFoodQuantity();
		
        foodQuantities.stream().forEach(food -> foodDTO.add(food.getFood().convertToFoodDTO()));
        return foodDTO;
   	}
}
