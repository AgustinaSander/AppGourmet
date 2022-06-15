package com.example.demo;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.domain.Food;
import com.example.demo.domain.Email;
import com.example.demo.domain.EmailNotification;
import com.example.demo.domain.ProfileCarnivorous;
import com.example.demo.domain.ProfileCeliac;
import com.example.demo.domain.ProfileVegetarian;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeBook;
import com.example.demo.domain.Subscription;
import com.example.demo.domain.User;
import com.example.demo.domain.enumerations.FoodGroup;
import com.example.demo.domain.enumerations.Unit;

@RunWith(MockitoJUnitRunner.class)
public class EmailNotificationTest {
	
	@Spy
	EmailNotification spyEmailNotification = Mockito.spy(EmailNotification.getEmailNotification());
	
	Recipe recipe = new Recipe(1, "New Recipe");
	User user1 = new User(1, "usuario1@email.com");
	User user2 = new User(2, "usuario2@email.com");
	User user3 = new User(3, "usuario3@email.com");
	User user4 = new User(4, "usuario4@email.com");
	List<Subscription> allSubscriptionsWithProfile = new ArrayList<>();
	
	RecipeBook recipeBook;
	
	@Before
	public void setUp() {
		//Create recipe that will be added to recipe book
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
		
		//Subscriptions to recipe book
		user1.subscribeRecipeBook(recipeBook, ProfileCarnivorous.getProfile());
		user1.subscribeRecipeBook(recipeBook, ProfileCeliac.getProfile());
		user2.subscribeRecipeBook(recipeBook, ProfileCarnivorous.getProfile());
		user3.subscribeRecipeBook(recipeBook, ProfileCeliac.getProfile());
		user4.subscribeRecipeBook(recipeBook, ProfileVegetarian.getProfile());
		
		//Get all subscriptions that could be interested on the recipe according to the profile
		allSubscriptionsWithProfile = recipeBook.getSubscriptionsAcordingToProfile(recipe);
		
		Mockito.doReturn(true).when(spyEmailNotification).sendEmailNotification(any(Email.class));
	}
	
	// ------ SEND NOTIFICATIONS TO USERS WITH SOME PROFILES ------
	
	@Test
	public void testSendNotificationsToUsersWithProfile() {
		//All subscription notifications are active
		List<User> usersThatMustReceiveEmail = List.of(user1,user4,user3);
		
		assertTrue(spyEmailNotification.notificateUsersAboutRecipe(allSubscriptionsWithProfile, recipe, recipeBook).containsAll(usersThatMustReceiveEmail));
	}
	
	@Test
	public void testSendNotificationsToUsersWithActiveSubscriptions() {
		//Some notifications are inactive
		Subscription subscriptionCeliac = user1.getSubscriptions().get(1);
		user1.turnOffNotifications(subscriptionCeliac);
		
		List<User> usersThatMustReceiveEmail = List.of(user4,user3);
		assertTrue(spyEmailNotification.notificateUsersAboutRecipe(allSubscriptionsWithProfile, recipe, recipeBook).containsAll(usersThatMustReceiveEmail));
	}
	
	// ------ NOT SEND NOTIFICATIONS TO USERS WITH NON-MATCHING PROFILES ------
	@Test
	public void testNotSendNotificationsToUsersBecauseProfileDontMatch() {
		//All subscription notifications are active
		List<User> usersThatNotReceiveEmail = List.of(user2);
		assertFalse(spyEmailNotification.notificateUsersAboutRecipe(allSubscriptionsWithProfile, recipe, recipeBook).containsAll(usersThatNotReceiveEmail));
	}
	
	@Test
	public void testNotSendNotificationsToUsersBecauseProfileDontMatchAndNotificationsTurnOff() {
		//Some notifications are inactive
		Subscription subscriptionCeliac = user1.getSubscriptions().get(1);
		user1.turnOffNotifications(subscriptionCeliac);
		
		List<User> usersThatNotReceiveEmail = List.of(user2,user1);
		assertFalse(spyEmailNotification.notificateUsersAboutRecipe(allSubscriptionsWithProfile, recipe, recipeBook).containsAll(usersThatNotReceiveEmail));
	}

}
