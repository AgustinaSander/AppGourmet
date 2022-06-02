package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Food;
import domain.NotificationImpl;
import domain.ProfileCarnivorous;
import domain.ProfileCeliac;
import domain.ProfileVegetarian;
import domain.Recipe;
import domain.RecipeBook;
import domain.Subscription;
import domain.User;
import domain.enumerations.FoodGroup;
import domain.enumerations.Unit;

public class NotificationImplTest {
	
	NotificationImpl notification = NotificationImpl.getNotificationImpl();
	Recipe recipe = new Recipe(1, "New Recipe");
	User user1 = new User(1, "usuario1@email.com");
	User user2 = new User(2, "usuario2@email.com");
	User user3 = new User(3, "usuario3@email.com");
	User user4 = new User(4, "usuario4@email.com");
	
	RecipeBook recipeBook;
	
	@Before
	public void setUp() {
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
		
		recipeBook = new RecipeBook(1, "One week meals");
		
		
		user1.subscribeRecipeBook(recipeBook, ProfileCarnivorous.getProfile());
		user1.subscribeRecipeBook(recipeBook, ProfileCeliac.getProfile());
		user2.subscribeRecipeBook(recipeBook, ProfileCarnivorous.getProfile());
		user3.subscribeRecipeBook(recipeBook, ProfileCeliac.getProfile());
		user4.subscribeRecipeBook(recipeBook, ProfileVegetarian.getProfile());
	}
	
	// ------ SEND NOTIFICATIONS TO USERS WITH SOME PROFILES ------
	
	@Test
	public void testSendNotificationsToUsersWithProfile() {
		//All subscription notifications are active
		List<User> usersThatMustReceiveEmail = List.of(user1,user4,user3);
		assertTrue(notification.sendNotifications(recipe, recipeBook).containsAll(usersThatMustReceiveEmail));
	}
	
	@Test
	public void testSendNotificationsToUsersWithActiveSubscriptions() {
		//Some notifications are inactive
		Subscription subscriptionCeliac = user1.getSubscriptions().get(1);
		user1.turnOffNotifications(subscriptionCeliac);
		
		List<User> usersThatMustReceiveEmail = List.of(user4,user3);
		assertTrue(notification.sendNotifications(recipe, recipeBook).containsAll(usersThatMustReceiveEmail));
	}
	
	// ------ NOT SEND NOTIFICATIONS TO USERS WITH NON-MATCHING PROFILES ------
	@Test
	public void testNotSendNotificationsToUsersBecauseProfileDontMatch() {
		//All subscription notifications are active
		List<User> usersThatNotReceiveEmail = List.of(user2);
		assertFalse(notification.sendNotifications(recipe, recipeBook).containsAll(usersThatNotReceiveEmail));
	}
	
	@Test
	public void testNotSendNotificationsToUsersBecauseProfileDontMatchAndNotificationsTurnOff() {
		//Some notifications are inactive
		Subscription subscriptionCeliac = user1.getSubscriptions().get(1);
		user1.turnOffNotifications(subscriptionCeliac);
		
		List<User> usersThatNotReceiveEmail = List.of(user2,user1);
		assertFalse(notification.sendNotifications(recipe, recipeBook).containsAll(usersThatNotReceiveEmail));
	}

}
