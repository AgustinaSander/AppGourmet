package com.example.demo.domain;

import com.example.demo.domain.enumerations.FoodGroup;

public class ProfileVegan implements IProfile{

	private ProfileVegan() {};
	
	private static class ProfileVeganHolder{
		private static final ProfileVegan profileVegan = new ProfileVegan();
	}
	
	public static ProfileVegan getProfile() {
		return ProfileVeganHolder.profileVegan;
	}
	
	@Override
	public boolean isAllowedToEat(Recipe recipe) {
		return !(recipe.hasFoodGroup(FoodGroup.MILK_PRODUCTS) || recipe.hasFoodGroup(FoodGroup.MEATS));
	}

}
