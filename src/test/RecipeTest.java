package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
		Food food1 = new Food(1, "Tomatoe", 102, FoodGroup.FRUITS);
		Food food2 = new Food(2, "Chicken", 100, FoodGroup.MEATS);
		Food food3 = new Food(3, "Rice", 6, FoodGroup.CEREALS);
		Food food4 = new Food(4, "Basil", 15, FoodGroup.VEGETABLES);
		Food food5 = new Food(5, "Fish", 340, FoodGroup.MEATS);
		List<FoodQuantity> listFoodQuantity = new ArrayList<>();
		listFoodQuantity.add(new FoodQuantity(3,Unit.UNIT,food1));
		listFoodQuantity.add(new FoodQuantity(1,Unit.UNIT,food2));
		listFoodQuantity.add(new FoodQuantity(200,Unit.GRAM,food3));
		listFoodQuantity.add(new FoodQuantity(1,Unit.CN,food4));
		listFoodQuantity.add(new FoodQuantity(0.5, Unit.UNIT, food5));
		
		recipe = new Recipe(1, "Chicken salad", listFoodQuantity);
	}
	
	// ------ NUMBER OF CALORIES ------
	@Test
	public void testNumberOfCalories() {
		assertEquals(1791, recipe.getCalories());
	}
	
	// ------- HAS INGREDIENT -------
	@Test
	public void testHasIngredient1() {
		assertEquals(true, recipe.hasIngredient("Tomatoe"));
	}
	
	@Test
	public void testHasIngredient2() {
		assertEquals(true, recipe.hasIngredient("Chicken"));
	}

	@Test
	public void testHasIngredient3() {
		assertEquals(true, recipe.hasIngredient("Fish"));
	}
	
	@Test
	public void testHasIngredient4() {
		assertEquals(false, recipe.hasIngredient("Apple"));
	}
	
	@Test
	public void testHasIngredient5() {
		assertEquals(false, recipe.hasIngredient("tomatoe"));
	}
	
	// ------ HAS FOOD GROUP -----
	
	@Test
	public void testHasFoodGroupCereals() {
		assertEquals(true, recipe.hasFoodGroup(FoodGroup.CEREALS));
	}
	
	@Test
	public void testHasFoodGroupFruits() {
		assertEquals(true, recipe.hasFoodGroup(FoodGroup.FRUITS));
	}
	
	@Test
	public void testHasFoodGroupMeats() {
		assertEquals(true, recipe.hasFoodGroup(FoodGroup.MEATS));
	}
	
	@Test
	public void testHasFoodGroupVegetables() {
		assertEquals(true, recipe.hasFoodGroup(FoodGroup.VEGETABLES));
	}
	
	@Test
	public void testHasFoodGroupMilkProducts() {
		assertEquals(false, recipe.hasFoodGroup(FoodGroup.MILK_PRODUCTS));
	}
	
	@Test
	public void testHasFoodGroupLegumes() {
		assertEquals(false, recipe.hasFoodGroup(FoodGroup.LEGUMES));
	}
	
	// ------ NUMBER OF INGREDIENTS ------
	
	@Test
	public void testNumberOfIngredients() {
		assertEquals(5, recipe.getNumberOfIngredients());
	}
	
}
