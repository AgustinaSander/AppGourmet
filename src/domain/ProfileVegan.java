package domain;

import domain.enumerations.FoodGroup;

public class ProfileVegan implements Profile{

	@Override
	public boolean isAllowedToEat(Recipe recipe) {
		return !(recipe.hasFoodGroup(FoodGroup.MILK_PRODUCTS) | recipe.hasFoodGroup(FoodGroup.MEATS));
	}

}
