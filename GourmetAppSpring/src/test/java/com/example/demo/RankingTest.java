package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.demo.domain.Ranking;
import com.example.demo.domain.RankingSubscription;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeBook;

public class RankingTest {

	RecipeBook book1;
	RecipeBook book2;
	RecipeBook book3;
	List<Recipe> listRecipes = new ArrayList<>();
	int sizeListRecipes = 4;
	Ranking ranking;
	RankingSubscription rankingSubscriptionOfBook3;
	
	@Before
	public void setUp() {
		for(int i=1; i<= sizeListRecipes; i++) {
			listRecipes.add(new Recipe(i,"Recipe "+i));
		}
		book1 = new RecipeBook(1, "One week meals", listRecipes);
		
		List<Recipe> listRecipes2 = listRecipes.subList(0, sizeListRecipes-2);
		book2 = new RecipeBook(2, "Meals recipe book", listRecipes2);
		
		List<Recipe> listRecipes3 = listRecipes.subList(0, sizeListRecipes-3);
		book3 = new RecipeBook(3, "How to cook", listRecipes3);
		
		ranking = new Ranking(1, "Best meals 2022");
		ranking.addRecipeBook(book3);
		
		rankingSubscriptionOfBook3 = book3.getRankingSubscriptions().stream()
				.filter(subscription -> subscription.getRanking().equals(ranking))
				.toList().get(0);
	}
	
	// ------ ADD POINTS TO A NEW RECIPE IN RANKINGS ------
		@Test
		public void testAddRecipeWhenSubscriptionIsActiveMustAddPoints() {
			//Subscription of book3 is active, so if I add a recipe to the recipe book, points for recipe must be 10.
			Recipe recipe = new Recipe(sizeListRecipes+1, "New Recipe");
			book3.addRecipe(recipe);
			int pointsForBeingInRanking = ranking.getPointsForBeingInRanking();
			int pointsThatRecipeHasInRanking = recipe.getPointsForRanking().get(ranking.getId());
			
			//ranking.showRanking();
			assertEquals(pointsForBeingInRanking, pointsThatRecipeHasInRanking);
		}
		
		@Test
		public void testAddRecipeWhenSubscriptionIsDeactiveNotAddPoints() {
			ranking.deactivateSubscription(rankingSubscriptionOfBook3);
			
			//Subscription is deactive, so if I add a recipe to the recipe book, it won`t be added to the ranking.
			Recipe recipe = new Recipe(sizeListRecipes+2, "Recipe added when subscription is deactive");
			book3.addRecipe(recipe);
			
			int pointsForBeingInRanking = ranking.getPointsForBeingInRanking();
			int pointsThatRecipeHasInRanking = recipe.getPointsForRanking().containsKey(ranking.getId()) ? 
													recipe.getPointsForRanking().get(ranking.getId()) : 0;
			
			//ranking.showRanking();
			assertNotEquals(pointsForBeingInRanking, pointsThatRecipeHasInRanking);
		}
}
