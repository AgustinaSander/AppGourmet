package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Food;
import domain.FoodQuantity;
import domain.Recipe;
import domain.enumerations.FoodGroup;
import domain.enumerations.Unit;

public class RecipeTest {

	Recipe recipe;
	
	@Before
	public void setUp() {
		Food food1 = new Food(1, "Tomatoe", 102, FoodGroup.FRUITS, Unit.UNIT);
		Food food2 = new Food(2, "Chicken", 100, FoodGroup.MEATS, Unit.UNIT);
		Food food3 = new Food(3, "Rice", 6, FoodGroup.CEREALS, Unit.GRAM);
		Food food4 = new Food(4, "Basil", 0, FoodGroup.VEGETABLES, Unit.CN);
		Food food5 = new Food(5, "Fish", 340, FoodGroup.MEATS,  Unit.UNIT);
		Food food6 = new Food(6, "Salt", 2000, FoodGroup.OTHER, Unit.CN);
				
		recipe = new Recipe(1, "Chicken Salad");
		recipe.addIngredient(3, food1);
		recipe.addIngredient(1, food2);
		recipe.addIngredient(200, food3);
		recipe.addIngredient(1, food4);
		recipe.addIngredient(0.5, food5);
		recipe.addIngredient(6, food6);
		
		
	}
	
	// ------ NUMBER OF CALORIES ------
	@Test
	public void testNumberOfCalories() {
		assertEquals(1776, recipe.getCalories());
	}
	
	// ------- HAS INGREDIENT -------
	@Test
	public void testHasIngredientTomatoe() {
		assertTrue(recipe.hasIngredient("Tomatoe"));
	}
	
	@Test
	public void testHasIngredientApple() {
		assertFalse(recipe.hasIngredient("Apple"));
	}
	
	// ------ HAS FOOD GROUP -----
	
	@Test
	public void testHasFoodGroupCereals() {
		assertTrue(recipe.hasFoodGroup(FoodGroup.CEREALS));
	}
	
	@Test
	public void testHasFoodGroupMilkProducts() {
		assertFalse(recipe.hasFoodGroup(FoodGroup.MILK_PRODUCTS));
	}
	
	// ------ NUMBER OF INGREDIENTS ------
	
	@Test
	public void testNumberOfIngredients() {
		assertEquals(6, recipe.getNumberOfIngredients());
	}
	
}
