package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Food;
import domain.Recipe;
import domain.enumerations.FoodGroup;
import domain.enumerations.Profile;
import domain.enumerations.Unit;

public class RecipeTest {

	Recipe recipeChickenSalad = new Recipe(1, "Chicken Salad");
	Recipe recipeAppleCakeCeliacs = new Recipe(2, "Apple cake for celiacs");
	Recipe recipeAppleCake = new Recipe(3, "Apple cake");
	Recipe recipePasta = new Recipe(4, "Pasta with tomatoe sauce");
	
	@Before
	public void setUp() {
		
		//--- Recipe 1: chicken and fish ---
		Food[] foodRecipe1 = {
				new Food(1, "Tomatoe", 102, FoodGroup.FRUITS, Unit.UNIT),
				new Food(2, "Chicken", 100, FoodGroup.MEATS, Unit.UNIT),
				new Food(3, "Rice", 6, FoodGroup.CEREALS, Unit.GRAM),
				new Food(4, "Basil", 0, FoodGroup.VEGETABLES, Unit.CN),
				new Food(5, "Fish", 340, FoodGroup.MEATS,  Unit.UNIT),
				new Food(6, "Salt", 2000, FoodGroup.OTHER, Unit.CN)
		};
							
		recipeChickenSalad.addIngredient(3, foodRecipe1[0]);
		recipeChickenSalad.addIngredient(1, foodRecipe1[1]);
		recipeChickenSalad.addIngredient(200, foodRecipe1[2]);
		recipeChickenSalad.addIngredient(1, foodRecipe1[3]);
		recipeChickenSalad.addIngredient(0.5, foodRecipe1[4]);
		recipeChickenSalad.addIngredient(6, foodRecipe1[5]);
		
		//--- Recipe 2: apple cake for celiacs ---
		
		Food[] foodRecipe2 = {
				new Food(1, "Butter", 5, FoodGroup.MILK_PRODUCTS, Unit.GRAM),
				new Food(2,"Premixture", 7, FoodGroup.OTHER, Unit.GRAM),
				new Food(3,"Sugar", 4, FoodGroup.OTHER, Unit.GRAM),
				new Food(4,"Egg", 24, FoodGroup.MILK_PRODUCTS, Unit.UNIT),
				new Food(5,"Apple", 15, FoodGroup.FRUITS, Unit.UNIT),
				new Food(6,"Bake Powder", 10, FoodGroup.OTHER, Unit.SPOON)
		};
		
		recipeAppleCakeCeliacs.addIngredient(125,foodRecipe2[0]);
		recipeAppleCakeCeliacs.addIngredient(115,foodRecipe2[1]);
		recipeAppleCakeCeliacs.addIngredient(225,foodRecipe2[2]);
		recipeAppleCakeCeliacs.addIngredient(3,foodRecipe2[3]);
		recipeAppleCakeCeliacs.addIngredient(3,foodRecipe2[4]);
		recipeAppleCakeCeliacs.addIngredient(1,foodRecipe2[5]);
	
		
		//--- Recipe 3: apple cake ---
	
		Food[] foodRecipe3 = {
				new Food(1, "Butter", 5, FoodGroup.MILK_PRODUCTS,Unit.GRAM),
				new Food(2,"Flour", 7, FoodGroup.CEREALS,Unit.GRAM),
				new Food(3,"Sugar", 3, FoodGroup.OTHER,Unit.GRAM),
				new Food(4,"Egg", 24, FoodGroup.MILK_PRODUCTS,Unit.UNIT),
				new Food(5,"Apple", 15, FoodGroup.FRUITS, Unit.UNIT),
				new Food(6,"Bake Powder", 10, FoodGroup.OTHER, Unit.SPOON)
		};
		
		recipeAppleCake.addIngredient(125,foodRecipe3[0]);
		recipeAppleCake.addIngredient(120,foodRecipe3[1]);
		recipeAppleCake.addIngredient(225,foodRecipe3[2]);
		recipeAppleCake.addIngredient(3,foodRecipe3[3]);
		recipeAppleCake.addIngredient(3, foodRecipe3[4]);
		recipeAppleCake.addIngredient(1, foodRecipe3[5]);
		
		//--- Recipe 4: pasta with tomatoe sauce ---
		
		Food[] foodRecipe4 = {
				new Food(1, "Noodles", 2, FoodGroup.CEREALS, Unit.GRAM),
				new Food(2,"Tomatoe", 102, FoodGroup.FRUITS, Unit.UNIT),
				new Food(3,"Pepper", 43, FoodGroup.VEGETABLES, Unit.UNIT),
				new Food(4,"Condiment", 24, FoodGroup.VEGETABLES, Unit.CN),
				new Food(5,"Salt",14,FoodGroup.OTHER, Unit.CN)
		};
		
		recipePasta.addIngredient(350,foodRecipe4[0]);
		recipePasta.addIngredient(2,foodRecipe4[1]);
		recipePasta.addIngredient(0.5,foodRecipe4[2]);
		recipePasta.addIngredient(1,foodRecipe4[3]);
		recipePasta.addIngredient(1, foodRecipe4[4]);	

	}
	
	// ------ NUMBER OF CALORIES ------
	@Test
	public void testNumberOfCaloriesRecipe1() {
		assertEquals(1776, recipeChickenSalad.getCalories());
	}
	
	// ------- HAS INGREDIENT -------
	@Test
	public void testHasIngredientTomatoeRecipe1() {
		assertTrue(recipeChickenSalad.hasIngredient("Tomatoe"));
	}
	
	@Test
	public void testHasIngredientAppleRecipe1() {
		assertFalse(recipeChickenSalad.hasIngredient("Apple"));
	}
	
	// ------ HAS FOOD GROUP -----
	
	@Test

	public void testHasFoodGroupCerealsRecipe1() {
		assertTrue(recipeChickenSalad.hasFoodGroup(FoodGroup.CEREALS));
	}
	
	@Test
	public void testHasFoodGroupMilkProductsRecipe1() {
		assertFalse(recipeChickenSalad.hasFoodGroup(FoodGroup.MILK_PRODUCTS));
	}
	
	// ------ NUMBER OF INGREDIENTS ------
	
	@Test
	public void testNumberOfIngredientsRecipe1() {
		assertEquals(6, recipeChickenSalad.getNumberOfIngredients());
	}
	
	// ------ NUMBER OF MEAT CALORIES ------
	
	@Test
	public void testNumberOfMeatCaloriesRecipe1() {
		assertEquals(270, recipeChickenSalad.getMeatCalories());
	}
	
	// ------ MINIMIUM MEAT CALORIES ------
	
	@Test
	public void testMinMeatCaloriesRecipe1() {
		assertTrue(recipeChickenSalad.hasMinMeatCalories());
	}
	@Test
	public void testMinMeatCaloriesRecipe2() {
		assertFalse(recipeAppleCakeCeliacs.hasMinMeatCalories());
	}
	
	// ------ RECIPE SUITABLE FOR ------
	
	@Test
	public void testSuitableForCarnivorous() {
		assertTrue(recipeChickenSalad.suitableFor(Profile.CARNIVOROUS));
	}
	
	@Test
	public void testNotSuitableForCarnivorous() {
		assertFalse(recipeAppleCakeCeliacs.suitableFor(Profile.CARNIVOROUS));
	}
	
	@Test
	public void testSuitableForCeliacs() {
		assertTrue(recipeAppleCakeCeliacs.suitableFor(Profile.CELIAC));
	}
	
	@Test
	public void testNotSuitableForCeliacs() {
		assertFalse(recipeAppleCake.suitableFor(Profile.CELIAC));
	}
	
	@Test
	public void testSuitableForVegetarians() {
		assertTrue(recipeAppleCakeCeliacs.suitableFor(Profile.VEGETARIAN));
	}
	
	@Test
	public void testNotSuitableForVegetarians() {
		assertFalse(recipeChickenSalad.suitableFor(Profile.VEGETARIAN));
	}
	
	@Test
	public void testSuitableForVegans() {
		assertTrue(recipePasta.suitableFor(Profile.VEGAN));
	}
	
	@Test
	public void testNotSuitableForVegans() {
		assertFalse(recipeChickenSalad.suitableFor(Profile.VEGAN));
	}
	
}
