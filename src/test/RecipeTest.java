package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Food;
import domain.Recipe;
import domain.enumerations.FoodGroup;
import domain.enumerations.Profile;
import domain.enumerations.Unit;

public class RecipeTest {

	List<Recipe> recipes = new ArrayList<>();
	
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
							
		Recipe recipe1 = new Recipe(1, "Chicken Salad");
		recipe1.addIngredient(3, foodRecipe1[0]);
		recipe1.addIngredient(1, foodRecipe1[1]);
		recipe1.addIngredient(200, foodRecipe1[2]);
		recipe1.addIngredient(1, foodRecipe1[3]);
		recipe1.addIngredient(0.5, foodRecipe1[4]);
		recipe1.addIngredient(6, foodRecipe1[5]);
		
		//--- Recipe 2: apple cake for celiacs ---
		
		Food[] foodRecipe2 = {
				new Food(1, "Butter", 5, FoodGroup.MILK_PRODUCTS, Unit.GRAM),
				new Food(2,"Premixture", 7, FoodGroup.OTHER, Unit.GRAM),
				new Food(3,"Sugar", 4, FoodGroup.OTHER, Unit.GRAM),
				new Food(4,"Egg", 24, FoodGroup.MILK_PRODUCTS, Unit.UNIT),
				new Food(5,"Apple", 15, FoodGroup.FRUITS, Unit.UNIT),
				new Food(6,"Bake Powder", 10, FoodGroup.OTHER, Unit.SPOON)
		};
		
		Recipe recipe2 = new Recipe(2, "Apple cake for celiacs");
		recipe2.addIngredient(125,foodRecipe2[0]);
		recipe2.addIngredient(115,foodRecipe2[1]);
		recipe2.addIngredient(225,foodRecipe2[2]);
		recipe2.addIngredient(3,foodRecipe2[3]);
		recipe2.addIngredient(3,foodRecipe2[4]);
		recipe2.addIngredient(1,foodRecipe2[5]);
	
		
		//--- Recipe 3: apple cake ---
	
		Food[] foodRecipe3 = {
				new Food(1, "Butter", 5, FoodGroup.MILK_PRODUCTS,Unit.GRAM),
				new Food(2,"Flour", 7, FoodGroup.CEREALS,Unit.GRAM),
				new Food(3,"Sugar", 3, FoodGroup.OTHER,Unit.GRAM),
				new Food(4,"Egg", 24, FoodGroup.MILK_PRODUCTS,Unit.UNIT),
				new Food(5,"Apple", 15, FoodGroup.FRUITS, Unit.UNIT),
				new Food(6,"Bake Powder", 10, FoodGroup.OTHER, Unit.SPOON)
		};
		
		Recipe recipe3 = new Recipe(3, "Apple cake");
		recipe3.addIngredient(125,foodRecipe3[0]);
		recipe3.addIngredient(120,foodRecipe3[1]);
		recipe3.addIngredient(225,foodRecipe3[2]);
		recipe3.addIngredient(3,foodRecipe3[3]);
		recipe3.addIngredient(3, foodRecipe3[4]);
		recipe3.addIngredient(1, foodRecipe3[5]);
		
		//--- Recipe 4: pasta with tomatoe sauce ---
		
		Food[] foodRecipe4 = {
				new Food(1, "Noodles", 2, FoodGroup.CEREALS, Unit.GRAM),
				new Food(2,"Tomatoe", 102, FoodGroup.FRUITS, Unit.UNIT),
				new Food(3,"Pepper", 43, FoodGroup.VEGETABLES, Unit.UNIT),
				new Food(4,"Condiment", 24, FoodGroup.VEGETABLES, Unit.CN),
				new Food(5,"Salt",14,FoodGroup.OTHER, Unit.CN)
		};
		
		Recipe recipe4 = new Recipe(4, "Pasta with tomatoe sauce");
		recipe4.addIngredient(350,foodRecipe4[0]);
		recipe4.addIngredient(2,foodRecipe4[1]);
		recipe4.addIngredient(0.5,foodRecipe4[2]);
		recipe4.addIngredient(1,foodRecipe4[3]);
		recipe4.addIngredient(1, foodRecipe4[4]);	

		//--- Recipe 5: ham&cheese omelette ---
		
		Food[] foodRecipe5 = {
				new Food(1, "Egg", 24, FoodGroup.MILK_PRODUCTS,Unit.UNIT),
				new Food(2,"Ham", 10, FoodGroup.MEATS,Unit.GRAM),
				new Food(3,"Cheese", 4, FoodGroup.MILK_PRODUCTS,Unit.GRAM),
				new Food(4,"Condiment", 24, FoodGroup.VEGETABLES,Unit.CN)
		};
		
		Recipe recipe5 = new Recipe(5, "Ham&Cheese omelette");
		recipe5.addIngredient(2,foodRecipe5[0]);
		recipe5.addIngredient(50,foodRecipe5[1]);
		recipe5.addIngredient(50,foodRecipe5[2]);
		recipe5.addIngredient(1,foodRecipe5[3]);
		
		
		recipes.add(recipe1);
		recipes.add(recipe2);
		recipes.add(recipe3);
		recipes.add(recipe4);
		recipes.add(recipe5);

	}
	
	// ------ NUMBER OF CALORIES ------
	@Test
	public void testNumberOfCaloriesRecipe1() {
		assertEquals(1776, recipes.get(0).getCalories());
	}
	
	// ------- HAS INGREDIENT -------
	@Test
	public void testHasIngredientTomatoeRecipe1() {
		assertTrue(recipes.get(0).hasIngredient("Tomatoe"));
	}
	
	@Test
	public void testHasIngredientAppleRecipe1() {
		assertFalse(recipes.get(0).hasIngredient("Apple"));
	}
	
	// ------ HAS FOOD GROUP -----
	
	@Test

	public void testHasFoodGroupCerealsRecipe1() {
		assertTrue(recipes.get(0).hasFoodGroup(FoodGroup.CEREALS));
	}
	
	@Test
	public void testHasFoodGroupMilkProductsRecipe1() {
		assertFalse(recipes.get(0).hasFoodGroup(FoodGroup.MILK_PRODUCTS));
	}
	
	// ------ NUMBER OF INGREDIENTS ------
	
	@Test
	public void testNumberOfIngredientsRecipe1() {
		assertEquals(6, recipes.get(0).getNumberOfIngredients());
	}
	
	// ------ NUMBER OF MEAT CALORIES ------
	
	@Test
	public void testNumberOfMeatCaloriesRecipe1() {
		assertEquals(270, recipes.get(0).getMeatCalories());
	}
	
	@Test
	public void testNumberOfMeatCaloriesRecipe2() {
		assertEquals(0, recipes.get(1).getMeatCalories());
	}
	
	// ------ MINIMIUM MEAT CALORIES ------
	
	@Test
	public void testMinMeatCaloriesRecipe1() {
		assertTrue(recipes.get(0).hasMinMeatCalories());
	}
	@Test
	public void testMinMeatCaloriesRecipe2() {
		assertFalse(recipes.get(1).hasMinMeatCalories());
	}
	
	// ------ RECIPE SUITABLE FOR ------
	
	@Test
	public void testSuitableForCarnivorous() {
		assertTrue(recipes.get(0).suitableFor(Profile.CARNIVOROUS));
	}
	
	@Test
	public void testNotSuitableForCarnivorous() {
		assertFalse(recipes.get(1).suitableFor(Profile.CARNIVOROUS));
	}
	
	@Test
	public void testSuitableForCeliacs() {
		assertTrue(recipes.get(1).suitableFor(Profile.CELIAC));
	}
	
	@Test
	public void testNotSuitableForCeliacs() {
		assertFalse(recipes.get(2).suitableFor(Profile.CELIAC));
	}
	
	@Test
	public void testSuitableForVegetarians() {
		assertTrue(recipes.get(1).suitableFor(Profile.VEGETARIAN));
	}
	
	@Test
	public void testNotSuitableForVegetarians() {
		assertFalse(recipes.get(0).suitableFor(Profile.VEGETARIAN));
	}
	
	@Test
	public void testSuitableForVegans() {
		assertTrue(recipes.get(3).suitableFor(Profile.VEGAN));
	}
	
	@Test
	public void testNotSuitableForVegans() {
		assertFalse(recipes.get(1).suitableFor(Profile.VEGAN));
	}
	
}
