package com.example.demo;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeBook;
import com.example.demo.repositories.FoodQuantityRepository;
import com.example.demo.repositories.FoodRepository;
import com.example.demo.repositories.RecipeBookRepository;
import com.example.demo.repositories.RecipeRepository;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodQuantity;
import com.example.demo.domain.enumerations.FoodGroup;
import com.example.demo.domain.enumerations.Unit;

@SpringBootTest
@RunWith(SpringRunner.class)
class RecipeBookPersistenceTest {
	@Autowired
	RecipeRepository recipeRepository;
	@Autowired
	RecipeBookRepository recipeBookRepository;
	@Autowired
	FoodRepository foodRepository;
	@Autowired
	FoodQuantityRepository foodQuantityRepository;
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void createRecipeBook() {
		RecipeBook recipeBook = getRecipeBook();
		//System.out.println(recipeBook);
		List<Recipe> recipes = recipeBook.getListRecipes();
		for(Recipe recipe : recipes) {
			saveRecipe(recipe);
		}
		
		recipeBookRepository.save(recipeBook);
		System.out.println(recipeBookRepository.findAll());
	}
      
	@Test
	public void readRecipeBook() {
    
	}
	
	@Test
	public void updateRecipeBook() {
		
	}
	
	@Test
	public void deleteRecipeBook() {
		
	}
	
	private void saveRecipe(Recipe recipe) {
		List<FoodQuantity> foodQuantities = recipe.getFoodQuantity();
		for(FoodQuantity foodQuantity : foodQuantities) {
			foodRepository.save(foodQuantity.getFood());
		}
		// WORKS System.out.println(foodRepository.findAll());
		foodQuantityRepository.saveAll(foodQuantities);
		// WORKS System.out.println(foodQuantityRepository.findAll());
		recipeRepository.save(recipe);
		// WORKS System.out.println(recipeRepository.findAll());
	}

	private RecipeBook getRecipeBook() {
		RecipeBook recipeBook = new RecipeBook("RecipeBook for Test");
		Recipe recipe = getRecipe();
		
		recipeBook.addRecipe(recipe);
		//System.out.println(recipeBook);
		return recipeBook;
	}
	
	private Recipe getRecipe() {
		Recipe recipeChickenSalad = new Recipe(1, "Chicken Salad");
		Food[] ingredients = {
				new Food(1, "Tomatoe", 102, FoodGroup.FRUITS, Unit.UNIT),
				new Food(2, "Chicken", 100, FoodGroup.MEATS, Unit.UNIT),
				new Food(3, "Rice", 6, FoodGroup.CEREALS, Unit.GRAM),
				new Food(4, "Basil", 0, FoodGroup.VEGETABLES, Unit.CN),
				new Food(5, "Fish", 340, FoodGroup.MEATS,  Unit.UNIT),
				new Food(6, "Salt", 2000, FoodGroup.OTHER, Unit.CN)
		};
		
		recipeChickenSalad.addIngredient(3, ingredients[0]);
		recipeChickenSalad.addIngredient(1, ingredients[1]);
		recipeChickenSalad.addIngredient(200, ingredients[2]);
		recipeChickenSalad.addIngredient(1, ingredients[3]);
		recipeChickenSalad.addIngredient(0.5, ingredients[4]);
		recipeChickenSalad.addIngredient(6, ingredients[5]);
		return recipeChickenSalad;
	}
	
}
