package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.example.demo.domain.DTO.RecipeBookDTO;
import com.example.demo.domain.DTO.RecipeDTO;

@Entity
public class RecipeBook{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recipebook_id")
	private int id;
	private String title;
	@ManyToMany(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinTable(name="recipe_recipebook", joinColumns=@JoinColumn(name="recipebook_id"), 
	inverseJoinColumns=@JoinColumn(name="recipe_id"))
	private List<Recipe> listRecipes;
	@Transient
	private List<Subscription> subscriptions = new ArrayList<>();
	@Transient
	private List<RankingSubscription> rankingSubscriptions = new ArrayList<>();

	
	public RecipeBook() {}
	
	public RecipeBook(String title) {
		this.title = title;
		this.listRecipes = new ArrayList<>();
		this.subscriptions = new ArrayList<>();
		this.rankingSubscriptions = new ArrayList<>();
	}
	
	public RecipeBook(int id, String title) {
		this.id = id;
		this.title = title;
		this.listRecipes = new ArrayList<>();
		this.subscriptions = new ArrayList<>();
		this.rankingSubscriptions = new ArrayList<>();
	}

	public RecipeBook(String title, List<Recipe> listRecipes) {
		this.title = title;
		this.listRecipes = listRecipes;
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
	
	public RecipeBookDTO convertToRecipeBookDTO() {
		RecipeBookDTO recipeBookDTO = new RecipeBookDTO(id, title);
		List<RecipeDTO> recipesDTO = new ArrayList<>();
		listRecipes.stream().forEach(recipe -> recipesDTO.add(recipe.convertToRecipeDTO()));
		recipeBookDTO.setListRecipes(recipesDTO);
		
		return recipeBookDTO;
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
