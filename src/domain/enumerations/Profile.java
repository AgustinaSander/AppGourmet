package domain.enumerations;

import java.util.ArrayList;
import java.util.List;

public enum Profile {
	VEGAN(FoodGroup.MEATS, FoodGroup.MILK_PRODUCTS),
	CELIAC(FoodGroup.CEREALS),
	VEGETARIAN(FoodGroup.MEATS),
	CARNIVOROUS();
	
	private final FoodGroup foodRestriction1, foodRestriction2;
	
	Profile(){
		this.foodRestriction1 = null;
		this.foodRestriction2 = null;
	}
	
	Profile(FoodGroup foodRestriction){
		this.foodRestriction1 = foodRestriction;
		this.foodRestriction2 = null;
	}
	
	Profile(FoodGroup foodRestriction1, FoodGroup foodRestriction2){
		this.foodRestriction1 = foodRestriction1;
		this.foodRestriction2 = foodRestriction2;
	}

	public FoodGroup getFoodRestriction1() {
		return foodRestriction1;
	}

	public FoodGroup getFoodRestriction2() {
		return foodRestriction2;
	}

	public List<FoodGroup> getRestrictedFoodGroups() {
		List<FoodGroup> restrictedFoods = new ArrayList<>();
		if(this.getFoodRestriction1() != null) restrictedFoods.add(this.foodRestriction1);
		if(this.getFoodRestriction2() != null) restrictedFoods.add(this.foodRestriction2);
		return restrictedFoods;
	}
}
