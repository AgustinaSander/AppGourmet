package com.example.demo.domain.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodQuantity;
import com.example.demo.domain.Recipe;


public class RecipeDTO {
	private int id = 0;
	private String title;
	private List<FoodQuantityDTO> foodQuantity = new ArrayList<>();

	public RecipeDTO() {}
	
	public RecipeDTO(String title) {
		this.title = title;
		this.foodQuantity = new ArrayList<>();
	}
	
	public RecipeDTO(int id, String title) {
		this.id = id;
		this.title = title;
		this.foodQuantity = new ArrayList<>();
	}

	public RecipeDTO(int id, String title, List<FoodQuantityDTO> foodQuantity) {
		this.id = id;
		this.title = title;
		this.foodQuantity = foodQuantity;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<FoodQuantityDTO> getFoodQuantity() {
		return foodQuantity;
	}

	public void setFoodQuantity(List<FoodQuantityDTO> foodQuantity) {
		this.foodQuantity = foodQuantity;
	}

	public void setId(int id) {
		this.id = id;
		
	}
	
	public Recipe convertRecipeObject() {
		List<FoodQuantityDTO> foodQuantitiesDTO = getFoodQuantity();
		List<FoodQuantity> foodQuantities = new ArrayList<>();
		boolean hasId = getId() != 0;
		
		if(hasId || (foodQuantitiesDTO.size() != 0 && getTitle().length()!=0)) {
			foodQuantitiesDTO.stream().forEach(foodQuantityDTO -> {
				FoodDTO foodDTO = foodQuantityDTO.getFood();
				Food food = new Food(foodDTO.getName(), foodDTO.getCalories(), foodDTO.getFoodGroup(), foodDTO.getUnit());
				foodQuantities.add(new FoodQuantity(foodQuantityDTO.getQuantity(), food));
			});
		} else {
			throw new IllegalArgumentException("Recipe must have an id or title and at least one ingredient.");
		}

		return new Recipe(getId(), getTitle(), foodQuantities);
	}
}
