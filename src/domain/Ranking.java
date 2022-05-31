package domain;

import java.util.ArrayList;
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
	private final int points = 10;
	private List<RankingSubscription> rankingSubscriptions;
	
	public Ranking(int id, String name) {
		this.id = id;
		this.name = name;
		this.rankingSubscriptions = new ArrayList<>();
	}

	public void addRecipeBook(RecipeBook recipeBook) {
		boolean subscriptionExists = !getRankingSubscriptions().stream()
															.noneMatch(subscription -> subscription.getRecipeBook().equals(recipeBook));
															
		if(!subscriptionExists) {	
			addPointsToAllRecipes (recipeBook);
			RankingSubscription subscription = new RankingSubscription(this, recipeBook);
			recipeBook.addRankingSubscription(subscription);
			rankingSubscriptions.add(subscription);
			//System.out.println("SUSCRIPCIONES AL RANKING: "+ getRankingSubscriptions());
		}
		else {
			//System.out.println("Recipe book is already subscribed to the ranking.");
		}
	}
	
	public void removeRecipeBook(RankingSubscription subscription) {
		if(getRankingSubscriptions().contains(subscription)) {
			RecipeBook recipeBook = subscription.getRecipeBook();
			subtractPointsToAllRecipes(recipeBook);
			recipeBook.removeRankingSubscription(subscription);
			getRankingSubscriptions().remove(subscription);
		}
	}
	
	public void addPointsToAllRecipes(RecipeBook recipeBook) {
		recipeBook.getListRecipes().stream().forEach(recipe -> {
			Map<Integer, Integer> pointsForRanking = recipe.getPointsForRanking();
			
			int rankingPoints = pointsForRanking.get(this.id) == null ? this.points : pointsForRanking.get(this.id) + this.points;
			recipe.getPointsForRanking().put(this.id, rankingPoints);
		});
	}
	
	public void addPointsToRecipe(Recipe recipe) {
		Map<Integer, Integer> pointsForRanking = recipe.getPointsForRanking();
		
		int rankingPoints = pointsForRanking.get(this.id) == null ? this.points : pointsForRanking.get(this.id) + this.points;
		recipe.getPointsForRanking().put(this.id, rankingPoints);
	}

	/*public void subtractPointsToAllRecipes(RecipeBook recipeBook) {
		recipeBook.getListRecipes().stream().forEach(recipe -> {
			int initialPoints = recipe.getPointsForRanking().get(this.id);
			int actualPoints = initialPoints - points;
			if(actualPoints == 0) {
				recipe.getPointsForRanking().remove(this.id);
			}
			else {
				recipe.getPointsForRanking().put(this.id, actualPoints);
			}
		});
	}*/
	
	public void showRanking() {
		Map <Recipe, Integer> ranking = getRankingPositions();
		System.out.println("Ranking "+this.name);
		
		int index = 1;
		for(Recipe recipe : ranking.keySet()){
			System.out.println(index + " - " + recipe.getTitle() + " : " + ranking.get(recipe) + "points");
			index++;
		};
	}
	
	public Map<Recipe, Integer> getRankingPositions(){
		Map <Recipe, Integer> ranking = new HashMap<>();
		Set<Recipe> recipesSubscribed = getRankedRecipes();
	
		recipesSubscribed.stream().forEach(recipe -> {
			ranking.put(recipe, recipe.getPointsForRanking().get(this.id));
		});
		
		return sortRankingByPoints(ranking);
	}
	
	public Set<Recipe> getRankedRecipes(){
		Set<Recipe> recipesSubscribed = new HashSet<>();
		
		getRankingSubscriptions().stream()
			.filter(subscription -> subscription.isActive())
			.forEach(subscription -> {
				recipesSubscribed.addAll(subscription.getRecipeBook().getListRecipes());
			});	
		
		return recipesSubscribed;
	}
	
	public Map<Recipe, Integer> sortRankingByPoints(Map<Recipe,Integer> ranking){
		Map<Recipe, Integer> sortedRanking = ranking.entrySet().stream()
								        .sorted(Entry.comparingByValue())
								        .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	
		return sortedRanking;
	}
	
	public void activateSubscription(RankingSubscription subscription) {
		if(getRankingSubscriptions().contains(subscription)) {
			subscription.setActive(true);
			addPointsToAllRecipes(subscription.getRecipeBook());
		}
	}
	
	public void deactivateSubscription(RankingSubscription subscription) {
		if(getRankingSubscriptions().contains(subscription)) {
			subscription.setActive(false);
			subtractPointsToAllRecipes(subscription.getRecipeBook()); //no deberia restarlo
		}
	}
	
	public List<RankingSubscription> getRankingSubscriptions() {
		return rankingSubscriptions;
	}
	
	public List<RankingSubscription> getActiveRankingSubscriptions(){
		return getRankingSubscriptions().stream().filter(subscription -> subscription.isActive()).toList();
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
	
}


