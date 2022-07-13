package com.example.demo.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.example.demo.domain.DTO.FoodQuantityDTO;
import com.example.demo.domain.DTO.RecipeDTO;
import com.example.demo.domain.enumerations.FoodGroup;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recipe_id")
	private int id;
	private String title;
	@OneToMany(fetch = FetchType.EAGER)
	private List<FoodQuantity> foodQuantity;
	@Transient
	private Map<Integer, Integer> pointsForRanking;
	
	public Recipe() {}
	
	public Recipe(String title) {
		this.title = title;
		this.foodQuantity = new ArrayList<>();
		this.pointsForRanking = new HashMap<>();
	}
	
	public Recipe(int id, String title) {
		this.id = id;
		this.title = title;
		this.foodQuantity = new ArrayList<>();
		this.pointsForRanking = new HashMap<>();
	}

	public Recipe(String title, List<FoodQuantity> foodQuantity) {
		this.title = title;
		this.foodQuantity = foodQuantity;
	}

	public Recipe(int id, String title, List<FoodQuantity> foodQuantity) {
		this.id = id;
		this.title = title;
		this.foodQuantity = foodQuantity;
		this.pointsForRanking = new HashMap<>();
	}
	
	private List<Food> getIngredients(){
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
	
	public int getMeatCalories() {
		 List<FoodQuantity> meatIngredients = getFoodQuantity().stream().filter(food -> food.getFood().getFoodGroup() == FoodGroup.MEATS).toList();
		
		return meatIngredients.stream().mapToInt(meat -> (int)( meat.getQuantity()*meat.getFood().getCalories())).sum();
	}
	
	public List<IProfile> getProfilesAllowedToEat() {
		List<IProfile> profiles = new ArrayList<>();
		
		if(ProfileCarnivorous.getProfile().isAllowedToEat(this))
			profiles.add(ProfileCarnivorous.getProfile());
		if(ProfileCeliac.getProfile().isAllowedToEat(this))
			profiles.add(ProfileCeliac.getProfile());
		if(ProfileVegan.getProfile().isAllowedToEat(this))
			profiles.add(ProfileVegan.getProfile());
		if(ProfileVegetarian.getProfile().isAllowedToEat(this))
			profiles.add(ProfileVegetarian.getProfile());
		
		return profiles;
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

	public Map<Integer, Integer> getPointsForRanking() {
		return pointsForRanking;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", title=" + title + ", foodQuantity=" + foodQuantity + ", pointsForRanking="
				+ pointsForRanking + "]";
	}

	public RecipeDTO convertToRecipeDTO() {
		RecipeDTO recipeDTO = new RecipeDTO(id, title);
		List<FoodQuantityDTO> foodQuantitiesDTO = new ArrayList<>();
		foodQuantity.stream().forEach(food -> foodQuantitiesDTO.add(food.convertToFoodQuantityDTO()));
		recipeDTO.setFoodQuantity(foodQuantitiesDTO);
	
		return recipeDTO;
	}

}
