package com.example.demo.DAO;

import java.util.List;

import com.example.demo.domain.Recipe;

public interface IRecipeDAO {
	public List<Recipe> getAllRecipes();
	public Recipe getRecipeById(int id);
	public Recipe createRecipe(Recipe recipe);
	public Recipe updateRecipe(Recipe recipe);
}
