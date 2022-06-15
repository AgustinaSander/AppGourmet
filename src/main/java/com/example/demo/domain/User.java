package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class User {
	private int id;
	private String email;
	private List<Subscription> subscriptions;
	
	public User(int id, String email) {
		this.id = id;
		this.email = email;
		this.subscriptions = new ArrayList<>();
	}
	
	public User(int id, String email, List<Subscription> subscriptions) {
		this.id = id;
		this.email = email;
		this.subscriptions = subscriptions;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + "]";
	}

	public boolean subscribeRecipeBook(RecipeBook recipeBook, IProfile profile) {
		if(recipeBook == null || profile == null) return false;
		
		boolean isSuscribed = getSubscriptions().isEmpty() ? false : 
					getSubscriptions().stream()
					.anyMatch(subscription -> subscription.getRecipeBook() == recipeBook && subscription.getProfile() == profile);
		
		
		if(isSuscribed) {
			return false;
		}
		else {
			Subscription subscription = new Subscription(this, profile, recipeBook);
			recipeBook.getSubscriptions().add(subscription);
			getSubscriptions().add(subscription);
			return true;
		}
	}
	
	public boolean unsubscribeRecipeBook(RecipeBook recipeBook, IProfile profile) {
		if(recipeBook == null || profile == null || getSubscriptions().isEmpty()) return false;
		
		Optional<Subscription> unsubscribe = getSubscriptions().stream()
									.filter(subscription -> subscription.getRecipeBook() == recipeBook && subscription.getProfile() == profile)
									.findAny();
		if(unsubscribe.isPresent()) {
			getSubscriptions().remove(unsubscribe.get());
			recipeBook.getSubscriptions().remove(unsubscribe.get());
			return true;
		}
		return false;
	}
	
	public void turnOnNotifications(Subscription subscription) {
		if(hasSubscription(subscription)) {
			subscription.setNotification(true);
		}
	}
	
	public void turnOffNotifications(Subscription subscription) {
		if(hasSubscription(subscription)) {
			subscription.setNotification(false);
		}
	}

	private boolean hasSubscription(Subscription hasSubscription) {
		Optional<Subscription> exists = getSubscriptions().stream().filter(subscription -> subscription.equals(hasSubscription)).findAny();
		
		return exists.isPresent();
	}
}
