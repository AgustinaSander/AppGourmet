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
	
	@Test
	public void testNumberOfCalories() {
		assertEquals(1791, recipe.getCalories());
	}
	
	@Test
	public void testHasIngredient() {
		assertEquals(true, recipe.hasIngredient("Tomatoe"));
		assertEquals(true, recipe.hasIngredient("Chicken"));
		assertEquals(true, recipe.hasIngredient("Fish"));
		assertEquals(false, recipe.hasIngredient("Apple"));
		assertEquals(false, recipe.hasIngredient("tomatoe"));
	}

	@Test
	public void testHasFoodGroup() {
		assertEquals(true, recipe.hasFoodGroup(FoodGroup.CEREALS));
		assertEquals(true, recipe.hasFoodGroup(FoodGroup.FRUITS));
		assertEquals(true, recipe.hasFoodGroup(FoodGroup.MEATS));
		assertEquals(true, recipe.hasFoodGroup(FoodGroup.VEGETABLES));
		assertEquals(false, recipe.hasFoodGroup(FoodGroup.MILK_PRODUCTS));
		assertEquals(false, recipe.hasFoodGroup(FoodGroup.LEGUMES));
	}
	
	@Test
	public void testNumberOfIngredients() {
		assertEquals(5, recipe.getNumberOfIngredients());
	}
	
}
