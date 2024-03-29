package com.example.demo.domain;

public class Subscription {
	private int id;
	private User user;
	private IProfile profile;
	private RecipeBook recipeBook;
	private boolean notification;
	
	public Subscription(User user, IProfile profile, RecipeBook recipeBook) {
		this.user = user;
		this.profile = profile;
		this.recipeBook = recipeBook;
		this.notification = true;
	}
	
	public Subscription(User user, IProfile profile, RecipeBook recipeBook, boolean notification) {
		this.user = user;
		this.profile = profile;
		this.recipeBook = recipeBook;
		this.notification = notification;
	}

	public Subscription(int id, User user, IProfile profile, RecipeBook recipeBook, boolean notification) {
		this.id = id;
		this.user = user;
		this.profile = profile;
		this.recipeBook = recipeBook;
		this.notification = notification;
	}

	public int getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}
	
	public IProfile getProfile() {
		return profile;
	}
	
	public void setProfile(IProfile profile) {
		this.profile = profile;
	}
	
	public RecipeBook getRecipeBook() {
		return recipeBook;
	}
	
	public void setRecipeBook(RecipeBook recipeBook) {
		this.recipeBook = recipeBook;
	}
	
	public boolean isNotification() {
		return notification;
	}
	
	public void setNotification(boolean notification) {
		this.notification = notification;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", user=" + user + ", profile=" + profile + ", recipeBook=" + recipeBook
				+ ", notification=" + notification + "]";
	}
	
	
}
