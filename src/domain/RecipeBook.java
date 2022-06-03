package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecipeBook{
	private int id;
	private String title;
	private List<Recipe> listRecipes;
	private List<Subscription> subscriptions;
	private List<RankingSubscription> rankingSubscriptions;
	
	public RecipeBook(int id, String title) {
		this.id = id;
		this.title = title;
		this.listRecipes = new ArrayList<>();
		this.subscriptions = new ArrayList<>();
		this.rankingSubscriptions = new ArrayList<>();
	}

	public RecipeBook(int id, String title, List<Recipe> listRecipes) {
		this.id = id;
		this.title = title;
		this.listRecipes = listRecipes;
		this.subscriptions = new ArrayList<>();
		this.rankingSubscriptions = new ArrayList<>();
	}
	
	public int getNumberOfRecipes(){
		return listRecipes.isEmpty() ? 0 : listRecipes.size();
	}
	
	public boolean addRecipe(Recipe newRecipe) {
	
		if(newRecipe != null && !getListRecipes().contains(newRecipe)) {
			listRecipes.add(newRecipe);	
			List<Subscription> allSubscriptionsWithProfile = getSubscriptionsAcordingToProfile(newRecipe);
			EmailNotification.getEmailNotification().notificateUsersAboutRecipe(allSubscriptionsWithProfile, newRecipe, this);
			
			getRankingSubscriptions().stream()
									.filter(subscription -> subscription.isActive())
									.forEach(subscription -> subscription.getRanking().addPointsToRecipe(newRecipe));
			
			return true;
		}
		return false;
	}
	
	public List<Subscription> getSubscriptionsAcordingToProfile(Recipe recipe) {
		List<IProfile> profilesAllowedToEat = recipe.getProfilesAllowedToEat();
		return getSubscriptionsWithProfile(profilesAllowedToEat);
	};
	
	public List<Subscription> getSubscriptionsWithProfile(List<IProfile> profiles) {
		List<Subscription> subscriptions = new ArrayList<>();
		
		for(IProfile profile : profiles) {
			subscriptions.addAll(getSubscriptions().stream()
										.filter(subscription -> subscription.getProfile() == profile)
										.toList());
		}
		
		return subscriptions;		
	}
	
	public boolean removeRecipe(Recipe deleteRecipe) {
		if(deleteRecipe == null) return false;
		
		Optional<Recipe> delete = getListRecipes().stream().filter(recipe -> recipe.equals(deleteRecipe)).findAny();
		return delete.isPresent() ?  getListRecipes().remove(delete.get()) : false;
	}
	
	public void addRankingSubscription(RankingSubscription subscription) {
		rankingSubscriptions.add(subscription);
	}
	
	public void removeRankingSubscription(RankingSubscription subscription) {
		if(getRankingSubscriptions().contains(subscription)) {
			getRankingSubscriptions().remove(subscription);
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

	public List<RankingSubscription> getRankingSubscriptions() {
		return rankingSubscriptions;
	}

	@Override
	public String toString() {
		return "RecipeBook [id=" + id + ", title=" + title + ", listRecipes=" + listRecipes + "]";
	}
	
	
}
