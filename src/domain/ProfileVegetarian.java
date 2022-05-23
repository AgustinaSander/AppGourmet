package domain;

public class ProfileVegetarian implements Profile{
	
	@Override
	public boolean isAllowedToEat(Recipe recipe) {
		return recipe.getMeatCalories() == 0;
	}

}
