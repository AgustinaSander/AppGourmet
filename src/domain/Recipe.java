package domain;

import java.util.ArrayList;
import java.util.List;

import domain.enumerations.FoodGroup;
import domain.enumerations.Profile;


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
		return foodQuantity.stream()
				.mapToInt(food -> (int)(food.getQuantity()*food.getFood().getCalories()))
				.sum();
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
	
	public void addIngredient(double quantity, Food food) {
		this.foodQuantity.add(new FoodQuantity(quantity, food));
	}
	
	public boolean removeIngredient (FoodQuantity food) {
		return this.foodQuantity.remove(food);
	}
	
	public List<FoodQuantity> getMeatIngredients(){
		return foodQuantity.stream().filter(f -> f.getFood().getFoodGroup() == FoodGroup.MEATS).toList();
	}
	
	public int getMeatCalories() {
		List<FoodQuantity> meatIngredients = getMeatIngredients();
		
		return meatIngredients.stream().mapToInt(m -> (int)( m.getQuantity()*m.getFood().getCalories())).sum();
	}
	
	public boolean hasMinMeatCalories() {
		return this.getMeatCalories() >= 200 ? true : false;
	}
	
	public boolean suitableFor(Profile profile) {
		List<FoodGroup> restrictedFoodGroups = profile.getRestrictedFoodGroups();
		boolean isSuitable = restrictedFoodGroups.stream().noneMatch(fg -> this.hasFoodGroup(fg));
		if(profile == Profile.CARNIVOROUS && isSuitable) {
			isSuitable = hasMinMeatCalories();
		}
		
		return isSuitable;
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

	public void setFoodQuantity(List<FoodQuantity> foodQuantity) {
		this.foodQuantity = foodQuantity;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", title=" + title + ", foodQuantity=" + foodQuantity + "]";
	}

	
}
