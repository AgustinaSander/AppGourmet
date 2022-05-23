package domain;

import domain.enumerations.FoodGroup;

public class ProfileCeliac implements Profile{

	@Override
	public boolean isAllowedToEat(Recipe recipe) {
		return !recipe.hasFoodGroup(FoodGroup.CEREALS);
	}

}
