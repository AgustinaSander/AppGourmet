package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodQuantity;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeBook;
import com.example.demo.domain.enumerations.FoodGroup;
import com.example.demo.domain.enumerations.Unit;
import com.example.demo.repositories.FoodQuantityRepository;
import com.example.demo.repositories.FoodRepository;
import com.example.demo.repositories.RecipeBookRepository;
import com.example.demo.repositories.RecipeRepository;


@SpringBootApplication
@Import({ClassConfig.class})
public class GourmetAppSpringApplication {
	
	@Autowired
	private RecipeBookRepository recipeBookRepository;
	@Autowired
	private FoodRepository foodRepository;
	@Autowired
	private FoodQuantityRepository foodQuantityRepository;
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Component
	class DataSetup implements ApplicationRunner{
		@Override
		public void run(ApplicationArguments args) throws Exception{
			RecipeBook recipeBook = getRecipeBook();
			List<Recipe> recipes = recipeBook.getListRecipes();
			
			for(Recipe recipe : recipes) {
				saveRecipe(recipe);
			}
			recipeBookRepository.save(recipeBook);
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GourmetAppSpringApplication.class, args);
		
	}

	private void saveRecipe(Recipe recipe) {
		List<FoodQuantity> foodQuantities = recipe.getFoodQuantity();
		for(FoodQuantity foodQuantity : foodQuantities) {
			foodRepository.save(foodQuantity.getFood());
		}
		foodQuantityRepository.saveAll(foodQuantities);
		recipeRepository.save(recipe);
	}
	
	private RecipeBook getRecipeBook() {
		RecipeBook recipeBook = new RecipeBook("RecipeBook API");
		Recipe recipe = getRecipe();
		
		recipeBook.addRecipe(recipe);
		return recipeBook;
	}
	
	private Recipe getRecipe() {
		Recipe recipeChickenSalad = new Recipe("Chicken Salad API");
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
