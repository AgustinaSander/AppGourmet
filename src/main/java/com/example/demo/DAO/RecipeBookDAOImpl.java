package com.example.demo.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeBook;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.RecipeBookRepository;

@Service
public class RecipeBookDAOImpl implements IRecipeBookDAO{
	@Autowired
	RecipeBookRepository recipeBookRepository;
	@Autowired
	RecipeDAOImpl recipeDAO;
	
	@Override
	public List<RecipeBook> getAllRecipeBooks() {
		List<RecipeBook> recipeBooks = (List<RecipeBook>) recipeBookRepository.findAll();
		return recipeBooks;
	}

	@Override
	public RecipeBook getRecipeBookById(int id) {
		RecipeBook recipeBook = recipeBookRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "recipe books"));
		return recipeBook;
	}

	@Override
	public List<Recipe> getRecipesFromRecipeBook(int id) {
		RecipeBook recipeBook = recipeBookRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "recipe books"));
		return recipeBook.getListRecipes();
	}

	@Override
	public RecipeBook createRecipeBook(RecipeBook recipeBook) {
		return recipeBookRepository.save(recipeBook);
	}

	@Override
	public RecipeBook updateRecipeBook(RecipeBook recipeBook) {
		getRecipeBookById(recipeBook.getId());
		return recipeBookRepository.save(recipeBook);
	}
	
	@Override
	public RecipeBook addRecipeToRecipeBook(int idRecipeBook, int idRecipe) {
		RecipeBook recipeBook = getRecipeBookById(idRecipeBook);
		Recipe recipe = recipeDAO.getRecipeById(idRecipe);
		recipeBook.addRecipe(recipe);
		System.out.println(recipeBook);
		return updateRecipeBook(recipeBook);
	}

}
