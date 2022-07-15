package com.example.demo.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.RecipeDAOImpl;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.DTO.RecipeDTO;
import com.example.demo.exceptions.NotFoundException;

@RestController
@CrossOrigin
public class RecipeController {
	@Autowired
	private RecipeDAOImpl recipeDAO;
	
	@GetMapping("/recipes")
	CollectionModel<EntityModel<RecipeDTO>> all() throws Exception {
		List<EntityModel<RecipeDTO>> recipeDTO = new ArrayList<>();
		List<Recipe> recipes = recipeDAO.getAllRecipes();
		if(recipes.size()==0) {
	       throw new NotFoundException("recipe");
		}
		recipes.stream().forEach(recipe -> recipeDTO.add(toModel(recipe.convertToRecipeDTO())));
		
		return CollectionModel.of(recipeDTO, linkTo(methodOn(RecipeController.class).all()).withSelfRel());
   	}
	
	@GetMapping("/recipes/{id}")
	EntityModel<RecipeDTO> getRecipeById(@PathVariable int id) throws Exception{
		RecipeDTO recipeDTO = new RecipeDTO();
		Recipe recipe = recipeDAO.getRecipeById(id);
		recipeDTO = recipe.convertToRecipeDTO();
		return toModel(recipeDTO);
	}
	
	
	@PostMapping("/recipes")
	Recipe addRecipe(@RequestBody RecipeDTO recipeDTO){
		Recipe recipe = recipeDTO.convertRecipeObject();
		return recipeDAO.createRecipe(recipe);
	}

	@PutMapping("/recipes/{id}")
	Recipe updateRecipe(@PathVariable int id, @RequestBody RecipeDTO recipeDTO) throws Exception{
		recipeDTO.setId(id);
		Recipe modifiedRecipe = recipeDTO.convertRecipeObject();
		
		return recipeDAO.updateRecipe(modifiedRecipe);
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
