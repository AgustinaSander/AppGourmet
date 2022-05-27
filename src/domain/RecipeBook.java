package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class RecipeBook implements Notification{
	private int id;
	private String title;
	private List<Recipe> listRecipes;
	private List<Subscription> subscriptions;
	
	public RecipeBook(int id, String title) {
		this.id = id;
		this.title = title;
		this.listRecipes = new ArrayList<>();
		this.subscriptions = new ArrayList<>();
	}

	public RecipeBook(int id, String title, List<Recipe> listRecipes) {
		this.id = id;
		this.title = title;
		this.listRecipes = listRecipes;
		this.subscriptions = new ArrayList<>();
	}
	
	public int getNumberOfRecipes(){
		return listRecipes.isEmpty() ? 0 : listRecipes.size();
	}
	
	public boolean addRecipe(Recipe newRecipe) {
	
		if(newRecipe != null && !getListRecipes().contains(newRecipe)) {
			listRecipes.add(newRecipe);
			List<Profile> profilesAllowedToEat = newRecipe.getProfilesAllowedToEat();
			sendNotificationToUsers(profilesAllowedToEat);
			return true;
		}
		return false;
	}
	
	@Override
	public void sendNotificationToUsers(List<Profile> profiles) {
		List<User> users = new ArrayList<>();
		List<Subscription> subscriptions = new ArrayList<>();
		
		for(Profile profile : profiles) {
			subscriptions.addAll(getSubscriptions().stream()
										.filter(subscription -> subscription.getProfile() == profile && subscription.isNotification())
										.toList());
		}
		
		System.out.println("SUSCRIPCIONES ENCONTRADAS ACTIVADAS");
		subscriptions.stream().forEach(subscription -> {
			users.add(subscription.getUser());
			System.out.println("User: "+subscription.getUser().getEmail() + "- Profile: "+subscription.getProfile());
		});
		
		System.out.println("NOTIFICACIONES");
		users.stream().distinct().forEach(user -> System.out.println(user));			
	}
	
	public boolean removeRecipe(Recipe deleteRecipe) {
		if(deleteRecipe == null) return false;
		
		Optional<Recipe> delete = getListRecipes().stream().filter(recipe -> recipe.equals(deleteRecipe)).findAny();
		 
		try {
			return getListRecipes().remove(delete.get());
		}
		catch(NoSuchElementException exception) {
			return false;
		}
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Recipe> getListRecipes() {
		return listRecipes;
	}
	
	public void setListRecipes(List<Recipe> listRecipes) {
		this.listRecipes = listRecipes;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	@Override
	public String toString() {
		return "RecipeBook [id=" + id + ", title=" + title + ", listRecipes=" + listRecipes + "]";
	}
	
	
}
