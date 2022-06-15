package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Ranking {
	private int id;
	private String name;
	private List<RankingSubscription> rankingSubscriptions;
	private static final int POINTS_FOR_BEING_IN_RANKING = 10;
	
	public Ranking(int id, String name) {
		this.id = id;
		this.name = name;
		this.rankingSubscriptions = new ArrayList<>();
	}

	public boolean addRecipeBook(RecipeBook recipeBook) {
		boolean subscriptionExists = !getRankingSubscriptions().stream().noneMatch(subscription -> subscription.getRecipeBook().equals(recipeBook));
															
		if(!subscriptionExists) {	
			addPointsToAllRecipes (recipeBook);
			RankingSubscription subscription = new RankingSubscription(this, recipeBook);
			recipeBook.addRankingSubscription(subscription);
			rankingSubscriptions.add(subscription);
			return true;
		}
		
		return false;
	}
	
	public boolean removeSubscription(RankingSubscription subscription) {
		if(getRankingSubscriptions().contains(subscription)) {
			RecipeBook recipeBook = subscription.getRecipeBook();
			getRankingSubscriptions().remove(subscription);
			removeRecipeBook(recipeBook, subscription);
			return true;
		}
		
		return false;
	}
	
	public void removeRecipeBook(RecipeBook recipeBook, RankingSubscription subscription) {
			recipeBook.removeRankingSubscription(subscription);
			subtractPointsToAllRecipes(recipeBook);
	}
	
	public void addPointsToAllRecipes(RecipeBook recipeBook) {
		recipeBook.getListRecipes().stream().forEach(recipe -> {
			addPointsToRecipe(recipe);
		});
	}
	
	public void addPointsToRecipe(Recipe recipe) {
		Map<Integer, Integer> pointsForRanking = recipe.getPointsForRanking();
		
		int rankingPoints = pointsForRanking.containsKey(id) ? pointsForRanking.get(id) + POINTS_FOR_BEING_IN_RANKING : POINTS_FOR_BEING_IN_RANKING;
		recipe.getPointsForRanking().put(id, rankingPoints);
	}

	public void subtractPointsToAllRecipes(RecipeBook recipeBook) {
		recipeBook.getListRecipes().stream().forEach(recipe -> {
			int initialPoints = recipe.getPointsForRanking().containsKey(id) ? recipe.getPointsForRanking().get(id) : 0;
			int actualPoints = initialPoints - POINTS_FOR_BEING_IN_RANKING;
			
			if(actualPoints <= 0) {
				recipe.getPointsForRanking().remove(id);
			}
			else {
				recipe.getPointsForRanking().put(id, actualPoints);
			}
		});
	}
	
	public void showRanking() {
		Map <Recipe, Integer> ranking = getRankingPositions();
		System.out.println("Ranking "+name);
		
		int index = 1;
		if(ranking.size() != 0) {
			for(Recipe recipe : ranking.keySet()){
				System.out.println(index + " - " + recipe.getTitle() + " : " + ranking.get(recipe) + "points");
				index++;
			};
		} else {
			System.out.println("No recipes availables.");
		}
	}
	
	public Map<Recipe, Integer> getRankingPositions(){
		Map <Recipe, Integer> ranking = new HashMap<>();
		Set<Recipe> recipesSubscribed = getRankedRecipes();
	
		recipesSubscribed.stream().forEach(recipe -> {
			if(recipe.getPointsForRanking().get(id) != null) { 
				int pointsInRanking = recipe.getPointsForRanking().get(id);
				ranking.put(recipe, pointsInRanking);
			}
		});
		
		getInactiveRankingSubscriptions().stream().forEach(subscription -> {
			List<Recipe> recipesInSubscriptionInactive = subscription.getRecipeBook().getListRecipes();
			
			recipesInSubscriptionInactive.stream().forEach(recipe -> {
				if(recipesSubscribed.contains(recipe)) {
					int pointsForRecipeInMap = ranking.get(recipe);
					pointsForRecipeInMap -= POINTS_FOR_BEING_IN_RANKING;
					
					if(pointsForRecipeInMap==0) {
						ranking.remove(recipe); 
					}
					else {
						ranking.put(recipe, pointsForRecipeInMap);
					}
				}
			});
		});
		
		return sortRankingByPoints(ranking);
	}
	
	public Set<Recipe> getRankedRecipes(){
		Set<Recipe> recipesSubscribed = new HashSet<>();
		
		getActiveRankingSubscriptions().stream().forEach(subscription -> {
			recipesSubscribed.addAll(subscription.getRecipeBook().getListRecipes());
		});	
		
		return recipesSubscribed;
	}
	
	public Map<Recipe, Integer> sortRankingByPoints(Map<Recipe,Integer> ranking){
		Map<Recipe, Integer> sortedRanking = ranking.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
								        	.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		return sortedRanking;
	}
	
	public void activateSubscription(RankingSubscription subscription) {
		if(getRankingSubscriptions().contains(subscription)) {
			subscription.setActive(true);
		}
	}
	
	public void deactivateSubscription(RankingSubscription subscription) {
		if(getRankingSubscriptions().contains(subscription)) {
			subscription.setActive(false);
		}
	}
	
	public List<RankingSubscription> getRankingSubscriptions() {
		return rankingSubscriptions;
	}
	
	public List<RankingSubscription> getActiveRankingSubscriptions(){
		return getRankingSubscriptions().stream().filter(subscription -> subscription.isActive()).toList();
	}
	
	public List<RankingSubscription> getInactiveRankingSubscriptions(){
		return getRankingSubscriptions().stream().filter(subscription -> !subscription.isActive()).toList();
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPointsForBeingInRanking() {
		return POINTS_FOR_BEING_IN_RANKING;
	}
	
}


