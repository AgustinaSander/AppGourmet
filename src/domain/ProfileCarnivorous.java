package domain;

public class ProfileCarnivorous implements Profile{

	@Override
	public boolean isAllowedToEat(Recipe recipe) {
		return recipe.getMeatCalories() >= 200;
	}

}
