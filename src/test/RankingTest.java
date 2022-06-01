package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Ranking;
import domain.RankingSubscription;
import domain.Recipe;
import domain.RecipeBook;

public class RankingTest {

	RecipeBook book1;
	RecipeBook book2;
	RecipeBook book3;
	List<Recipe> listRecipes = new ArrayList<>();
	int sizeListRecipes = 4;
	Ranking ranking;
	
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
	}

	@Test
	public void testAddRecipeBook() {
		assertTrue(ranking.addRecipeBook(book1));
		ranking.addRecipeBook(book2);
	}
	
	@Test
	public void testAddSameRecipeBook() {
		ranking.addRecipeBook(book1);
		assertFalse(ranking.addRecipeBook(book1));
	}
	
	@Test
	public void testShowRanking() {
		ranking.addRecipeBook(book1);
		ranking.addRecipeBook(book2);
		
		
		//ranking.showRanking();
		/*System.out.println(ranking.getActiveRankingSubscriptions());
		System.out.println("---");
		//ranking.activateSubscription(sub);
		ranking.showRanking();
		System.out.println(ranking.getActiveRankingSubscriptions());
		
		ranking.removeRecipeBook(sub);
		ranking.showRanking();
		System.out.println(ranking.getRankingSubscriptions());*/
	}
	
	@Test
	public void testDeactivateSubscription() {
		ranking.addRecipeBook(book1);
		ranking.addRecipeBook(book2);
		RankingSubscription sub = ranking.getRankingSubscriptions().get(1);
		ranking.deactivateSubscription(sub);
		//ranking.showRanking();
		
		ranking.activateSubscription(sub);
		//ranking.showRanking();
	}
	
	@Test
	public void testRemoveRecipeBook() {
		ranking.addRecipeBook(book1);
		ranking.addRecipeBook(book2);
		RankingSubscription sub = ranking.getRankingSubscriptions().get(0);
		assertTrue(ranking.removeRecipeBook(sub));
		/*ranking.showRanking();
		ranking.deactivateSubscription(ranking.getRankingSubscriptions().get(0));
		ranking.showRanking();
		ranking.activateSubscription(ranking.getRankingSubscriptions().get(0));
		ranking.showRanking();
		*/
	}
	
	
	// ------ ADD POINTS TO A NEW RECIPE IN RANKINGS ------
	
		@Test
		public void testAddRecipeWhenSubscriptionIsActiveMustAddPoints() {
			//Subscription of book3 is active, so if I add a recipe to the recipe book, points for recipe must be 10.
			Recipe recipe = new Recipe(sizeListRecipes+1, "New Recipe");
			book3.addRecipe(recipe);
			int pointsForBeingInRanking = ranking.getPoints();
			int pointsThatRecipeHasInRanking = recipe.getPointsForRanking().get(ranking.getId());
			assertEquals(pointsForBeingInRanking, pointsThatRecipeHasInRanking);
			//ranking.showRanking();
		}
		
		/*@Test
		public void testAddRecipeWhenSubscriptionIsDeactiveNotAddPoints() {
			//ranking.showRanking();
			//Subscription is deactive, so if I add a recipe to the recipebook, not add to ranking.
			RankingSubscription rankingSubscription = book3.getRankingSubscriptions().stream()
													.filter(subscription -> subscription.getRanking().equals(ranking))
													.toList().get(0);
			ranking.deactivateSubscription(rankingSubscription);
			
			Recipe recipe = new Recipe(sizeListRecipes+1, "New Recipe");
			book3.addRecipe(recipe);
			//ranking.showRanking();
			//System.out.println(recipe.getPointsForRanking());
			ranking.activateSubscription(rankingSubscription);
			ranking.showRanking();
			Recipe recipe2 = new Recipe(sizeListRecipes+2, "New Recipe 2");
			book3.addRecipe(recipe2);
			ranking.showRanking();
		}*/
}
