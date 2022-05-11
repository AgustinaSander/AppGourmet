package domain.enumerations;

import java.util.ArrayList;
import java.util.List;

public enum Profile {
	VEGAN(FoodGroup.MEATS, FoodGroup.MILK_PRODUCTS),
	CELIAC(FoodGroup.CEREALS),
	VEGETARIAN(FoodGroup.MEATS),
	CARNIVOROUS();
	
	private final FoodGroup fg1, fg2;
	
	Profile(){
		this.fg1 = null;
		this.fg2 = null;
	}
	
	Profile(FoodGroup fg){
		this.fg1 = fg;
		this.fg2 = null;
	}
	
	Profile(FoodGroup fg1, FoodGroup fg2){
		this.fg1 = fg1;
		this.fg2 = fg2;
	}

	public FoodGroup getFg1() {
		return fg1;
	}

	public FoodGroup getFg2() {
		return fg2;
	}

	public List<FoodGroup> getRestrictedFoodGroups() {
		List<FoodGroup> restrictedFoods = new ArrayList<>();
		if(this.getFg1() == null) restrictedFoods.add(this.fg1);
		if(this.getFg2() == null) restrictedFoods.add(this.fg2);
		return restrictedFoods;
	}
}
