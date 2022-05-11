package domain;

import java.util.ArrayList;
import java.util.List;

import domain.enumerations.FoodGroup;

public class Recipe {
	private int id;
	private String title;
	private List<FoodQuantity> foodQuantity;
	
	public Recipe() {}
	
	public Recipe(int id, String title, List<FoodQuantity> foodQuantity) {
		this.id = id;
		this.title = title;
		this.foodQuantity = foodQuantity;
	}
	
	public List<Food> getIngredients(){
		List<Food> foodList = new ArrayList<>();
		foodQuantity.stream().forEach(f -> foodList.add(f.getFood()));
		return foodList;
	}
	
	public int getCalories() {
		return foodQuantity.stream().mapToInt(f -> (int)f.getQuantity()*f.getFood().getCalories()).sum();
	}
	
	public int getNumberOfIngredients() {
		return this.getIngredients().size();
	}
	
	public boolean hasIngredient(String ingredient) {
		return this.getIngredients().stream()
				.anyMatch(f -> f.getName().equals(ingredient));
	}
	
	public boolean hasFoodGroup(FoodGroup group) {
		return this.getIngredients().stream()
				.anyMatch(f -> f.getFoodGroup().equals(group));
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
	public List<FoodQuantity> getFoodQuantity() {
		return foodQuantity;
	}
	public void setFoodQuantity(List<FoodQuantity> foodQuantity) {
		this.foodQuantity = foodQuantity;
	}
	
	
}
