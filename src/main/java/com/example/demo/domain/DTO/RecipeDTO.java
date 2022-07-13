package com.example.demo.domain.DTO;

import java.util.ArrayList;
import java.util.List;


public class RecipeDTO {
	private int id;
	private String title;
	private List<FoodQuantityDTO> foodQuantity;

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
}
