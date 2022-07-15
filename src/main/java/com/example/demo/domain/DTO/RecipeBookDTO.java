package com.example.demo.domain.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodQuantity;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeBook;

public class RecipeBookDTO{
	private int id;
	private String title;
	private List<RecipeDTO> listRecipes = new ArrayList<>();
	
	public RecipeBookDTO() {}
	
	public RecipeBookDTO(String title) {
		this.title = title;
	}
	
	public RecipeBookDTO(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public RecipeBookDTO(int id, String title, List<RecipeDTO> listRecipes) {
		this.id = id;
		this.title = title;
		this.listRecipes = listRecipes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<RecipeDTO> getListRecipes() {
		return listRecipes;
	}

	public void setListRecipes(List<RecipeDTO> listRecipes) {
		this.listRecipes = listRecipes;
	}
	
	@Override
	public String toString() {
		return "RecipeBook [id=" + id + ", title=" + title + ", listRecipes=" + listRecipes + "]";
	}
	
	public RecipeBook convertRecipeBookObject() {
		if(getTitle() == null) {
			throw new IllegalArgumentException("Recipe book must have a title.");
		}
		List<RecipeDTO> listRecipesDTO = getListRecipes();
		List<Recipe> listRecipes = new ArrayList<>();
		
		listRecipesDTO.stream().forEach(recipeDTO -> {
			List<FoodQuantityDTO> listFoodQuantityDTO = recipeDTO.getFoodQuantity();
			List<FoodQuantity> listFoodQuantity = new ArrayList<>();
			listFoodQuantityDTO.stream().forEach(foodQuantityDTO -> {
				FoodDTO foodDTO = foodQuantityDTO.getFood();
				Food food = new Food(foodDTO.getName(), foodDTO.getCalories(), foodDTO.getFoodGroup(), foodDTO.getUnit());
				listFoodQuantity.add(new FoodQuantity(foodQuantityDTO.getQuantity(), food));
			});
			listRecipes.add(new Recipe(recipeDTO.getTitle(), listFoodQuantity));
		});
		return new RecipeBook(getId(), getTitle(), listRecipes);
	}
	
	
}
