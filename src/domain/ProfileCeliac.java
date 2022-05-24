package domain;

import domain.enumerations.FoodGroup;

public class ProfileCeliac implements Profile{

	private ProfileCeliac() {};
		
	private static class ProfileCeliacHolder{
		private static final ProfileCeliac profileCeliac = new ProfileCeliac();
	}
	
	public static ProfileCeliac getProfile() {
		return ProfileCeliacHolder.profileCeliac;
	}
	
	@Override
	public boolean isAllowedToEat(Recipe recipe) {
		return !recipe.hasFoodGroup(FoodGroup.CEREALS);
	}

}
