package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.ProfileCarnivorous;
import domain.ProfileCeliac;
import domain.ProfileVegan;
import domain.ProfileVegetarian;
import domain.RecipeBook;
import domain.Subscription;
import domain.User;

public class UserTest {

	User user;
	RecipeBook recipeBookNotSubscribedYet = new RecipeBook(1, "Recipes for dinner");
	RecipeBook recipeBookAlreadySubscribedTo = new RecipeBook(2,"Dessert recipes");
	
	@Before
	public void setUp(){
		List<Subscription> subscriptions = new ArrayList<>();
		subscriptions.add(new Subscription(user, ProfileCeliac.getProfile(), recipeBookAlreadySubscribedTo));
		user = new User(1, "usuario@email.com", subscriptions);
		recipeBookAlreadySubscribedTo.getSubscriptions().addAll(subscriptions);
	}
	
	// ------ SUBSCRIBE TO RECIPE BOOK ------
	
	@Test
	public void testSubscribeRecipeBook() {
		assertTrue(user.subscribeRecipeBook(recipeBookNotSubscribedYet, ProfileCarnivorous.getProfile()));
	}
	
	@Test
	public void testSuscribeRecipeBookWithSameProfile() {
		assertFalse(user.subscribeRecipeBook(recipeBookAlreadySubscribedTo, ProfileCeliac.getProfile()));
	}
	
	@Test
	public void testSuscribeRecipeBookWithAnotherProfile() {
		assertTrue(user.subscribeRecipeBook(recipeBookAlreadySubscribedTo, ProfileVegetarian.getProfile()));
	}
	
	@Test
	public void testSubscribeRecipeBookWhenRecipeIsNull() {
		assertFalse(user.subscribeRecipeBook(null, ProfileCarnivorous.getProfile()));
	}
	
	@Test
	public void testSubscribeRecipeBookWhenProfileIsNull() {
		assertFalse(user.subscribeRecipeBook(recipeBookNotSubscribedYet, null));
	}
	
	@Test
	public void testSubscribeRecipeBookWhenParametersAreNull() {
		assertFalse(user.subscribeRecipeBook(null, null));
	}
	
	// ------ UNSUBSCRIBE FROM RECIPE BOOK ------
	
	@Test
	public void testUnsubscribeRecipeBook() {
		assertTrue(user.unsubscribeRecipeBook(recipeBookAlreadySubscribedTo, ProfileCeliac.getProfile()));
	}
	
	@Test
	public void testUnsubscribeRecipeBookWhenProfileDoesntMatch() {
		assertFalse(user.unsubscribeRecipeBook(recipeBookAlreadySubscribedTo, ProfileVegan.getProfile()));
	}
	
	@Test
	public void testUnsubscribeRecipeBookWhenRecipeDoesntMatch() {
		assertFalse(user.unsubscribeRecipeBook(new RecipeBook(3, "New Recipe Book"), ProfileCarnivorous.getProfile()));
	}
	
	@Test
	public void testUnsubscribeRecipeBookWhenListIsEmpty() {
		user.unsubscribeRecipeBook(recipeBookAlreadySubscribedTo, ProfileCeliac.getProfile());
		assertFalse(user.unsubscribeRecipeBook(recipeBookAlreadySubscribedTo, ProfileCarnivorous.getProfile()));
	}
	
	@Test
	public void testUnsubscribeRecipeBookWhenProfileIsNull() {
		assertFalse(user.unsubscribeRecipeBook(recipeBookAlreadySubscribedTo, null));
	}
	
	@Test
	public void testUnsubscribeRecipeBookWhenRecipeBookIsNull() {
		assertFalse(user.unsubscribeRecipeBook(null, ProfileCeliac.getProfile()));
	}
	
	@Test
	public void testUnsubscribeRecipeBookWhenParametersAreNull() {
		assertFalse(user.subscribeRecipeBook(null, null));
	}
	
}
