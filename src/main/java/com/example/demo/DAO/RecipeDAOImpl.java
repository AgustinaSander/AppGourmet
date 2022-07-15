package com.example.demo.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Recipe;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.RecipeRepository;

@Service
public class RecipeDAOImpl implements IRecipeDAO{
	@Autowired
	RecipeRepository recipeRepository;

	@Override
	public List<Recipe> getAllRecipes() {
		List<Recipe> recipes = (List<Recipe>) recipeRepository.findAll();
		return recipes;
	}

	@Override
	public Recipe createRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public Recipe updateRecipe(Recipe modifiedRecipe) {
		getRecipeById(modifiedRecipe.getId());
		return recipeRepository.save(modifiedRecipe);
	}
	
	@Override
	public Recipe getRecipeById(int id) throws NotFoundException{
		return recipeRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "recipe"));
	}

}
