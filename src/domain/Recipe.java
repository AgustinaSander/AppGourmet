package domain;

import java.util.ArrayList;
import java.util.List;

import domain.enumerations.FoodGroup;
import domain.enumerations.Unit;

public class Recipe {
	private int id;
	private String title;
	private List<FoodQuantity> foodQuantity = new ArrayList<>();
	
	public Recipe() {}
	
	public Recipe(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public Recipe(int id, String title, List<FoodQuantity> foodQuantity) {
		this.id = id;
		this.title = title;
		this.foodQuantity = foodQuantity;
	}
	
	public List<Food> getIngredients(){
		List<Food> foodList = new ArrayList<>();
		foodQuantity.stream().forEach(food -> foodList.add(food.getFood()));
		return foodList;
	}
	
	public int getCalories() {
		return foodQuantity.stream().mapToInt(food -> (int)(food.getQuantity()*food.getFood().getCalories())).sum();
	}
	
	public int getNumberOfIngredients() {
		return this.getIngredients().size();
	}
	
	public boolean hasIngredient(String ingredient) {
		return this.getIngredients().stream()
				.anyMatch(food -> food.getName().equals(ingredient));
	}
	
	public boolean hasFoodGroup(FoodGroup group) {
		return this.getIngredients().stream()
				.anyMatch(food -> food.getFoodGroup().equals(group));
	}
	
	public List<FoodQuantity> addIngredient(double quantity, Unit unit, Food food) {
		if(unit.name() == Unit.CN.name()) {
			food.setCalories(0);
			quantity = 0;
		}
		
		getFoodQuantity().add(new FoodQuantity(quantity , unit, food));
		return getFoodQuantity();
	}
	
	public boolean removeIngredient (FoodQuantity food) {
		return getFoodQuantity().remove(food);
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
	
	public List<FoodQuantity> getFoodQuantity() {
		return foodQuantity;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", title=" + title + ", foodQuantity=" + foodQuantity + "]";
	}

	
}
