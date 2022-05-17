package domain;

import domain.enumerations.FoodGroup;

public class Food {
	private int id;
	private String name;
	private int calories;
	private FoodGroup foodGroup;
	
	public Food() {}
	
	public Food(int id, String name, int calories, FoodGroup foodGroup) {
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.foodGroup = foodGroup;
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

	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", calories=" + calories + ", foodGroup=" + foodGroup + "]";
	}
	
	
}
