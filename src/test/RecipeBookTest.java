package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Food;
import domain.ProfileCarnivorous;
import domain.ProfileCeliac;
import domain.ProfileVegetarian;
import domain.Ranking;
import domain.RankingSubscription;
import domain.Recipe;
import domain.RecipeBook;
import domain.User;
import domain.enumerations.FoodGroup;
import domain.enumerations.Unit;

public class RecipeBookTest {

	RecipeBook book;
	List<Recipe> listRecipes = new ArrayList<>();
	int sizeListRecipes = 4;
	
	Ranking ranking;
	
	@Before
	public void setUp() {
		for(int i=1; i<= sizeListRecipes; i++) {
			listRecipes.add(new Recipe(i,"Recipe "+i));
		}
		book = new RecipeBook(1, "One week meals", listRecipes);
		
		User user = new User(1, "usuario1@email.com");
		user.subscribeRecipeBook(book, ProfileCarnivorous.getProfile());
		user.subscribeRecipeBook(book, ProfileCeliac.getProfile());
		user.subscribeRecipeBook(book, ProfileVegetarian.getProfile());
		user.turnOffNotifications(user.getSubscriptions().get(2));
		user = new User(2, "usuario2@email.com");
		user.subscribeRecipeBook(book, ProfileCarnivorous.getProfile());
		user = new User(3, "usuario3@email.com");
		user.subscribeRecipeBook(book, ProfileCeliac.getProfile());
		user = new User(4, "usuario4@email.com");
		user.subscribeRecipeBook(book, ProfileVegetarian.getProfile());
		
		ranking = new Ranking(1, "Best meals 2022");
		ranking.addRecipeBook(book);
		
	}
	
	// ------ NUMBER OF RECIPES ------
	@Test
	public void testNumberOfRecipes() {
		assertEquals(sizeListRecipes, book.getNumberOfRecipes());
	}

	// ------ ADD NEW RECIPE ------
	@Test
	public void testAddRecipe() {
		Recipe recipe = new Recipe(sizeListRecipes+1, "New Recipe");
		Food[] foodRecipe = {
				new Food(1, "Butter", 5, FoodGroup.MILK_PRODUCTS, Unit.GRAM),
				new Food(2,"Premixture", 7, FoodGroup.OTHER, Unit.GRAM),
				new Food(3,"Sugar", 4, FoodGroup.OTHER, Unit.GRAM),
				new Food(4,"Egg", 24, FoodGroup.MILK_PRODUCTS, Unit.UNIT),
				new Food(5,"Apple", 15, FoodGroup.FRUITS, Unit.UNIT),
				new Food(6,"Bake Powder", 10, FoodGroup.OTHER, Unit.SPOON)
		};
		
		recipe.addIngredient(125,foodRecipe[0]);
		recipe.addIngredient(115,foodRecipe[1]);
		recipe.addIngredient(225,foodRecipe[2]);
		recipe.addIngredient(3,foodRecipe[3]);
		recipe.addIngredient(3,foodRecipe[4]);
		recipe.addIngredient(1,foodRecipe[5]);
		
		assertTrue(book.addRecipe(recipe));
	}
	
	@Test
	public void testAddSameRecipe() {
		assertFalse(book.addRecipe(listRecipes.get(0)));
	}
	
	@Test
	public void testAddNullRecipe() {
		assertFalse(book.addRecipe(null));
	}
	
	// ------ DELETE RECIPE ------
	@Test
	public void testRemoveRecipe() {
		assertTrue(book.removeRecipe(listRecipes.get(0)));
	}
	
	@Test
	public void testRemoveNotExistingRecipe() {
		assertFalse(book.removeRecipe(new Recipe(0, "Recipe never added")));
	}
	
	@Test
	public void testRemoveNullRecipe() {
		assertFalse(book.removeRecipe(null));
	}
	
	// ------ ADD POINTS TO A NEW RECIPE IN RANKINGS ------
	
	@Test
	public void testAddRecipeWhenSubscriptionIsActiveMustAddPoints() {
		//ranking.showRanking();
		//Subscription is active, so if I add a recipe to the recipebook, points must be 10 or more.
		Recipe recipe = new Recipe(sizeListRecipes+1, "New Recipe");
		book.addRecipe(recipe);
		
		//ranking.showRanking();
		//System.out.println(recipe.getPointsForRanking());
	}
	
	/* @Test
	public void testAddRecipeWhenSubscriptionIsDeactiveNotAddPoints() {
		//ranking.showRanking();
		//Subscription is deactive, so if I add a recipe to the recipebook, not add to ranking.
		RankingSubscription rankingSubscription = book.getRankingSubscriptions().stream()
												.filter(subscription -> subscription.getRanking().equals(ranking))
												.toList().get(0);
		ranking.deactivateSubscription(rankingSubscription);
		
		Recipe recipe = new Recipe(sizeListRecipes+1, "New Recipe");
		book.addRecipe(recipe);
		
		ranking.showRanking();
		//System.out.println(recipe.getPointsForRanking());
		ranking.activateSubscription(rankingSubscription);
		ranking.showRanking();
	}
	*/
}
