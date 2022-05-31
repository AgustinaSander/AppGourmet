package domain;

public class ProfileVegetarian implements IProfile{
	
	private ProfileVegetarian() {};
	
	private static class ProfileVegetarianHolder{
		private static final ProfileVegetarian profileVegetarian = new ProfileVegetarian();
	}
	
	public static ProfileVegetarian getProfile() {
		return ProfileVegetarianHolder.profileVegetarian;
	}
	
	@Override
	public boolean isAllowedToEat(Recipe recipe) {
		return recipe.getMeatCalories() == 0;
	}

}
