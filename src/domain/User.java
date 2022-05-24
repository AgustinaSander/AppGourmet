package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

	public boolean subscribeRecipeBook(RecipeBook recipeBook, Profile profile) {
		if(recipeBook == null || profile == null) return false;
		
		boolean isSuscribed = getSubscriptions().isEmpty() ? false : 
					getSubscriptions().stream()
					.anyMatch(subscription -> subscription.getRecipeBook() == recipeBook && subscription.getProfile() == profile);
		
		
		return isSuscribed ? false : getSubscriptions().add(new Subscription(this, profile, recipeBook));
	}
	
	public boolean unsubscribeRecipeBook(RecipeBook recipeBook, Profile profile) {
		if(recipeBook == null || profile == null || getSubscriptions().isEmpty()) return false;
		
		Optional<Subscription> unsuscribe = getSubscriptions().stream()
									.filter(subscription -> subscription.getRecipeBook() == recipeBook && subscription.getProfile() == profile)
									.findAny();
		try {
			return getSubscriptions().remove(unsuscribe.get());
		}
		catch(NoSuchElementException exception) {
			return false;
		}
		
	}
}
