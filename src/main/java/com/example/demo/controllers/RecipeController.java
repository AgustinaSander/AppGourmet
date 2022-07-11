package com.example.demo.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodQuantity;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.DTO.FoodDTO;
import com.example.demo.domain.DTO.FoodQuantityDTO;
import com.example.demo.domain.DTO.RecipeDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.RecipeRepository;

@RestController
@CrossOrigin
public class RecipeController {
	private final RecipeRepository recipeRepository;
	
	RecipeController (RecipeRepository recipeRepository){
		this.recipeRepository = recipeRepository;
	}
	
	@GetMapping("/recipes")
	CollectionModel<EntityModel<RecipeDTO>> all() throws Exception {
		List<EntityModel<RecipeDTO>> recipeDTO = new ArrayList<>();
		List<Recipe> recipes = (List<Recipe>) recipeRepository.findAll();
		if(recipes.size()==0) {
	       throw new NotFoundException("recipe");
		}
		recipes.stream().forEach(recipe -> recipeDTO.add(toModel(recipe.convertToRecipeDTO())));
		
		return CollectionModel.of(recipeDTO, linkTo(methodOn(RecipeController.class).all()).withSelfRel());
   	}
	
	@PostMapping("/recipes")
	Recipe addRecipe(@RequestBody RecipeDTO recipeDTO){
		Recipe recipe = convertRecipeObject(recipeDTO);
		return recipeRepository.save(recipe);
	}
	
	private Recipe convertRecipeObject(RecipeDTO recipeDTO) {
		List<FoodQuantityDTO> foodQuantitiesDTO = recipeDTO.getFoodQuantity();
		List<FoodQuantity> foodQuantities = new ArrayList<>();
		foodQuantitiesDTO.stream().forEach(foodQuantityDTO -> {
			FoodDTO foodDTO = foodQuantityDTO.getFood();
			Food food = new Food(foodDTO.getName(), foodDTO.getCalories(), foodDTO.getFoodGroup(), foodDTO.getUnit());
			foodQuantities.add(new FoodQuantity(foodQuantityDTO.getQuantity(), food));
		});
		
		return new Recipe(recipeDTO.getTitle(), foodQuantities);
	}

	@PutMapping("/recipes/{id}")
	Recipe updateRecipe(@PathVariable int id, @RequestBody RecipeDTO recipeDTO) throws Exception{
		Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "recipe"));
		Recipe modifiedRecipe = convertRecipeObject(recipeDTO);
		
		recipe.setTitle(modifiedRecipe.getTitle());
		recipe.setFoodQuantity(modifiedRecipe.getFoodQuantity());
		
		return recipeRepository.save(recipe);
	}
	
	
	public EntityModel<RecipeDTO> toModel(RecipeDTO recipe) {
	    try {
			return EntityModel.of(recipe, 
			    linkTo(methodOn(RecipeController.class).all()).withRel("recipes"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	 }
}
