package com.example.demo.domain.DTO;

import com.example.demo.domain.Food;
import com.example.demo.domain.enumerations.FoodGroup;
import com.example.demo.domain.enumerations.Unit;

public class FoodDTO {
	private int id;
	private String name;
	private int calories;
	private FoodGroup foodGroup;
	private Unit unit;
	
	public FoodDTO() {}
	
	public FoodDTO(int id, String name, int calories, FoodGroup foodGroup, Unit unit) {
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.foodGroup = foodGroup;
		this.unit = unit;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCalories() {
		return calories;
	}
	
	public void setCalories(int calories) {
		this.calories = calories;
	}
	
	public FoodGroup getFoodGroup() {
		return foodGroup;
	}
	
	public void setFoodGroup(FoodGroup foodGroup) {
		this.foodGroup = foodGroup;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public Food getFoodObject() {
		return new Food(id,name,calories,foodGroup,unit);
	}

}
