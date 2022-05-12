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
import domain.enumerations.Profile;
import domain.enumerations.Unit;

public class RecipeTest {

	public List<Recipe> recipes = new ArrayList<>();
	
	@Before
	public void setUp() {
		
		//--- Recipe 1: chicken and fish ---
		
		Food[] foods1 = {
				new Food(1, "Tomatoe", 102, FoodGroup.FRUITS),
				new Food(2, "Chicken", 500, FoodGroup.MEATS),
				new Food(3, "Rice", 6, FoodGroup.CEREALS),
				new Food(4, "Basil", 15, FoodGroup.VEGETABLES),
				new Food(5, "Fish", 340, FoodGroup.MEATS)
		};
		
		List<FoodQuantity> listFoodQuantity1 = new ArrayList<>();
		listFoodQuantity1.add(new FoodQuantity(3,Unit.UNIT,foods1[0]));
		listFoodQuantity1.add(new FoodQuantity(1,Unit.UNIT,foods1[1]));
		listFoodQuantity1.add(new FoodQuantity(200,Unit.GRAM,foods1[2]));
		listFoodQuantity1.add(new FoodQuantity(1,Unit.CN,foods1[3]));
		listFoodQuantity1.add(new FoodQuantity(0.25, Unit.UNIT, foods1[4]));
		
		//--- Recipe 2: apple cake for celiacs ---
		
		Food[] foods2 = {
				new Food(1, "Butter", 5, FoodGroup.MILK_PRODUCTS),
				new Food(2,"Premixture", 7, FoodGroup.OTHER),
				new Food(3,"Sugar", 4, FoodGroup.OTHER),
				new Food(4,"Egg", 24, FoodGroup.MILK_PRODUCTS),
				new Food(5,"Apple", 15, FoodGroup.FRUITS),
				new Food(6,"Bake Powder", 10, FoodGroup.OTHER)
		};
		List<FoodQuantity> listFoodQuantity2 = new ArrayList<>();
		listFoodQuantity2.add(new FoodQuantity(125,Unit.GRAM,foods2[0]));
		listFoodQuantity2.add(new FoodQuantity(115,Unit.GRAM,foods2[1]));
		listFoodQuantity2.add(new FoodQuantity(225,Unit.GRAM,foods2[2]));
		listFoodQuantity2.add(new FoodQuantity(3,Unit.UNIT,foods2[3]));
		listFoodQuantity2.add(new FoodQuantity(3, Unit.UNIT, foods2[4]));
		listFoodQuantity2.add(new FoodQuantity(1, Unit.SPOON, foods2[5]));
		
		//--- Recipe 3: apple cake ---
	
		Food[] foods3 = {
				new Food(1, "Butter", 5, FoodGroup.MILK_PRODUCTS),
				new Food(2,"Flour", 7, FoodGroup.CEREALS),
				new Food(3,"Sugar", 3, FoodGroup.OTHER),
				new Food(4,"Egg", 24, FoodGroup.MILK_PRODUCTS),
				new Food(5,"Apple", 15, FoodGroup.FRUITS),
				new Food(6,"Bake Powder", 10, FoodGroup.OTHER)
		};
		List<FoodQuantity> listFoodQuantity3 = new ArrayList<>();
		listFoodQuantity3.add(new FoodQuantity(125,Unit.GRAM,foods3[0]));
		listFoodQuantity3.add(new FoodQuantity(120,Unit.GRAM,foods3[1]));
		listFoodQuantity3.add(new FoodQuantity(225,Unit.GRAM,foods3[2]));
		listFoodQuantity3.add(new FoodQuantity(3,Unit.UNIT,foods3[3]));
		listFoodQuantity3.add(new FoodQuantity(3, Unit.UNIT, foods3[4]));
		listFoodQuantity3.add(new FoodQuantity(1, Unit.SPOON, foods3[5]));
		
		//--- Recipe 4: pasta with tomatoe sauce ---
		
		Food[] foods4 = {
				new Food(1, "Noodles", 2, FoodGroup.CEREALS),
				new Food(2,"Tomatoe", 102, FoodGroup.FRUITS),
				new Food(3,"Pepper", 43, FoodGroup.VEGETABLES),
				new Food(4,"Condiment", 24, FoodGroup.VEGETABLES),
				new Food(5,"Salt",14,FoodGroup.OTHER)
		};
		List<FoodQuantity> listFoodQuantity4 = new ArrayList<>();
		listFoodQuantity4.add(new FoodQuantity(350,Unit.GRAM,foods4[0]));
		listFoodQuantity4.add(new FoodQuantity(2,Unit.UNIT,foods4[1]));
		listFoodQuantity4.add(new FoodQuantity(0.5,Unit.UNIT,foods4[2]));
		listFoodQuantity4.add(new FoodQuantity(1,Unit.CN,foods4[3]));
		listFoodQuantity4.add(new FoodQuantity(1, Unit.CN, foods4[4]));	

		//--- Recipe 5: ham&cheese omelette ---
		
		Food[] foods5 = {
				new Food(1, "Egg", 24, FoodGroup.MILK_PRODUCTS),
				new Food(2,"Ham", 10, FoodGroup.MEATS),
				new Food(3,"Cheese", 4, FoodGroup.MILK_PRODUCTS),
				new Food(4,"Condiment", 24, FoodGroup.VEGETABLES)
		};
		List<FoodQuantity> listFoodQuantity5 = new ArrayList<>();
		listFoodQuantity5.add(new FoodQuantity(2,Unit.UNIT,foods5[0]));
		listFoodQuantity5.add(new FoodQuantity(50,Unit.GRAM,foods5[1]));
		listFoodQuantity5.add(new FoodQuantity(50,Unit.GRAM,foods5[2]));
		listFoodQuantity5.add(new FoodQuantity(1,Unit.CN,foods5[3]));
		
		
		recipes.add(new Recipe(1, "Chicken and fish", listFoodQuantity1));
		recipes.add(new Recipe(2, "Apple cake for celiacs", listFoodQuantity2));
		recipes.add(new Recipe(3, "Apple cake", listFoodQuantity3));
		recipes.add(new Recipe(4, "Pasta with tomatoe sauce", listFoodQuantity4));
		recipes.add(new Recipe(5, "Ham&Cheese omelette", listFoodQuantity5));
		
	}
	
	@Test
	public void testNumberOfCalories() {
		//--- Recipe 1: chicken and fish ---
		assertEquals(2106, recipes.get(0).getCalories());
		
		//--- Recipe 2: apple cake for celiacs ---
		assertEquals(2457, recipes.get(1).getCalories());
		
		//--- Recipe 3: apple cake ---
		assertEquals(2267, recipes.get(2).getCalories());
		
		//--- Recipe 4: pasta with tomatoe sauce ---
		assertEquals(963, recipes.get(3).getCalories());
		
		//--- Recipe 5: ham&cheese omelette ---
		assertEquals(772, recipes.get(4).getCalories());
	}
	
	@Test
	public void testHasIngredient() {
		//--- Recipe 1: chicken and fish ---
		Recipe recipe1 = recipes.get(0);
		assertEquals(true, recipe1.hasIngredient("Tomatoe"));
		assertEquals(true, recipe1.hasIngredient("Chicken"));
		assertEquals(true, recipe1.hasIngredient("Fish"));
		assertEquals(false, recipe1.hasIngredient("Apple"));
		assertEquals(false, recipe1.hasIngredient("tomatoe"));
	}

	@Test
	public void testHasFoodGroup() {
		//--- Recipe 1: chicken and fish ---
		Recipe recipe1 = recipes.get(0);
		assertEquals(true, recipe1.hasFoodGroup(FoodGroup.CEREALS));
		assertEquals(true, recipe1.hasFoodGroup(FoodGroup.FRUITS));
		assertEquals(true, recipe1.hasFoodGroup(FoodGroup.MEATS));
		assertEquals(true, recipe1.hasFoodGroup(FoodGroup.VEGETABLES));
		assertEquals(false, recipe1.hasFoodGroup(FoodGroup.MILK_PRODUCTS));
		assertEquals(false, recipe1.hasFoodGroup(FoodGroup.LEGUMES));
		assertEquals(false, recipe1.hasFoodGroup(FoodGroup.OTHER));
	}
	
	@Test
	public void testNumberOfIngredients() {
		//--- Recipe 1: chicken and fish ---
		assertEquals(5, recipes.get(0).getNumberOfIngredients());
		//--- Recipe 2: apple cake for celiacs ---
		assertEquals(6, recipes.get(1).getNumberOfIngredients());
		//--- Recipe 3: apple cake ---
		assertEquals(6, recipes.get(2).getNumberOfIngredients());
		//--- Recipe 4: pasta with tomatoe sauce ---
		assertEquals(5, recipes.get(3).getNumberOfIngredients());
		//--- Recipe 5: ham&cheese omelette ---
		assertEquals(4, recipes.get(4).getNumberOfIngredients());
	}
	
	@Test
	public void testNumberOfMeatCalories() {
		//--- Recipe 1: chicken and fish ---
		assertEquals(585, recipes.get(0).getMeatCalories());
		//--- Recipe 2: apple cake for celiacs ---
		assertEquals(0, recipes.get(1).getMeatCalories());
		//--- Recipe 3: apple cake ---
		assertEquals(0, recipes.get(2).getMeatCalories());
		//--- Recipe 4: pasta with tomatoe sauce ---
		assertEquals(0, recipes.get(3).getMeatCalories());
		//--- Recipe 5: ham&cheese omelette ---
		assertEquals(500, recipes.get(4).getMeatCalories());
	}
	
	@Test
	public void testMinMeatCalories() {
		//--- Recipe 1: chicken and fish ---
		assertEquals(true, recipes.get(0).hasMinMeatCalories());
		//--- Recipe 2: apple cake for celiacs ---
		assertEquals(false, recipes.get(1).hasMinMeatCalories());
		//--- Recipe 3: apple cake ---
		assertEquals(false, recipes.get(2).hasMinMeatCalories());
		//--- Recipe 4: pasta with tomatoe sauce ---
		assertEquals(false, recipes.get(3).hasMinMeatCalories());
		//--- Recipe 5: ham&cheese omelette ---
		assertEquals(true, recipes.get(4).hasMinMeatCalories());
	}
	
	@Test
	public void testSuitableFor() {
		//--- Recipe 1: chicken and fish ---
		Recipe recipe1 = recipes.get(0);
		assertEquals(false, recipe1.suitableFor(Profile.CELIAC));
		assertEquals(false, recipe1.suitableFor(Profile.VEGETARIAN));
		assertEquals(false, recipe1.suitableFor(Profile.VEGAN));
		assertEquals(true, recipe1.suitableFor(Profile.CARNIVOROUS));
		//--- Recipe 2: apple cake for celiacs ---
		Recipe recipe2 = recipes.get(1);
		assertEquals(true, recipe2.suitableFor(Profile.CELIAC));
		assertEquals(true, recipe2.suitableFor(Profile.VEGETARIAN));
		assertEquals(false, recipe2.suitableFor(Profile.VEGAN));
		assertEquals(false, recipe2.suitableFor(Profile.CARNIVOROUS));
		//--- Recipe 3: apple cake ---
		Recipe recipe3 = recipes.get(2);
		assertEquals(false, recipe3.suitableFor(Profile.CELIAC));
		assertEquals(true, recipe3.suitableFor(Profile.VEGETARIAN));
		assertEquals(false, recipe3.suitableFor(Profile.VEGAN));
		assertEquals(false, recipe3.suitableFor(Profile.CARNIVOROUS));
		//--- Recipe 4: pasta with tomatoe sauce ---
		Recipe recipe4 = recipes.get(3);
		assertEquals(false, recipe4.suitableFor(Profile.CELIAC));
		assertEquals(true, recipe4.suitableFor(Profile.VEGETARIAN));
		assertEquals(true, recipe4.suitableFor(Profile.VEGAN));
		assertEquals(false, recipe4.suitableFor(Profile.CARNIVOROUS));
		//--- Recipe 5: ham&cheese omelette ---
		Recipe recipe5 = recipes.get(4);
		assertEquals(true, recipe5.suitableFor(Profile.CELIAC));
		assertEquals(false, recipe5.suitableFor(Profile.VEGETARIAN));
		assertEquals(false, recipe5.suitableFor(Profile.VEGAN));
		assertEquals(true, recipe5.suitableFor(Profile.CARNIVOROUS));
	}
}
