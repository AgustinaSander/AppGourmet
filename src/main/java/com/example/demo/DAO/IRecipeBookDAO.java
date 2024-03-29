package com.example.demo.DAO;

import java.util.List;

import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeBook;

public interface IRecipeBookDAO {
	public List<RecipeBook> getAllRecipeBooks();
	public RecipeBook getRecipeBookById(int id);
	public List<Recipe> getRecipesFromRecipeBook(int id);
	public RecipeBook createRecipeBook(RecipeBook recipeBook);
	public RecipeBook updateRecipeBook(RecipeBook recipeBook);
	public RecipeBook addRecipeToRecipeBook(int idRecipeBook, int idRecipe);
}
