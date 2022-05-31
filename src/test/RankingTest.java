package test;

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
		ranking = new Ranking(1, "Best meals 2022");
	}

	@Test
	public void testAddRecipeBook() {
		ranking.addRecipeBook(book1);
		ranking.addRecipeBook(book2);
	}
	
	@Test
	public void testShowRanking() {
		ranking.addRecipeBook(book1);
		ranking.addRecipeBook(book2);
		RankingSubscription sub = ranking.getRankingSubscriptions().get(0);
		ranking.deactivateSubscription(sub);
		ranking.showRanking();
		System.out.println(ranking.getActiveRankingSubscriptions());
		System.out.println("---");
		ranking.activateSubscription(sub);
		ranking.showRanking();
		System.out.println(ranking.getActiveRankingSubscriptions());
		
		ranking.removeRecipeBook(sub);
		ranking.showRanking();
		System.out.println(ranking.getRankingSubscriptions());
	}
}
