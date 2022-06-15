package com.example.demo.domain;

public class ProfileCarnivorous implements IProfile{
	
	private ProfileCarnivorous() {};
	
	private static class ProfileCarnivorousHolder{
		private static final ProfileCarnivorous profileCarnivorous = new ProfileCarnivorous();
	}
	
	public static ProfileCarnivorous getProfile() {
		return ProfileCarnivorousHolder.profileCarnivorous;
	}
	
	@Override
	public boolean isAllowedToEat(Recipe recipe) {
		return recipe.getMeatCalories() >= 200;
	}

}
