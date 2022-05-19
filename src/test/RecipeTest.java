package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Food;
import domain.Recipe;
import domain.enumerations.FoodGroup;
import domain.enumerations.Unit;

public class RecipeTest {

	Recipe recipe;
	
	@Before
	public void setUp() {
		Food food1 = new Food(1, "Tomatoe", 102, FoodGroup.FRUITS);
		Food food2 = new Food(2, "Chicken", 100, FoodGroup.MEATS);
		Food food3 = new Food(3, "Rice", 6, FoodGroup.CEREALS);
		Food food4 = new Food(4, "Basil", 0, FoodGroup.VEGETABLES);
		Food food5 = new Food(5, "Fish", 340, FoodGroup.MEATS);
		Food food6 = new Food(6, "Salt", 2000, FoodGroup.OTHER);
		recipe = new Recipe(1, "Chicken Salad");
		recipe.addIngredient(3,Unit.UNIT,food1);
		recipe.addIngredient(1,Unit.UNIT,food2);
		recipe.addIngredient(200,Unit.GRAM,food3);
		recipe.addIngredient(1,Unit.CN,food4);
		recipe.addIngredient(0.5, Unit.UNIT, food5);
		//Although salt has 2000 calories and 6 CN unit, the method addIngredient() sets the calories in 0 and quantity in 1.
		recipe.addIngredient(6, Unit.CN, food6);
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
