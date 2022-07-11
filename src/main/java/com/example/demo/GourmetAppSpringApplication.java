package com.example.demo;

import java.util.ArrayList;
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
			/*List<RecipeBook> recipeBooks = getRecipeBooks();
			recipeBooks.stream().forEach(recipeBook -> {
				List<Recipe> recipes = recipeBook.getListRecipes();
				for(Recipe recipe : recipes) {
					saveRecipe(recipe);
				}
				recipeBookRepository.save(recipeBook);
			});*/
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
	
	private List<RecipeBook> getRecipeBooks() {
		List<RecipeBook> recipeBooks = new ArrayList<>();
		RecipeBook recipeBook1 = new RecipeBook("Recipes 2022");
		RecipeBook recipeBook2 = new RecipeBook("Best dessert recipes");
		RecipeBook recipeBook3 = new RecipeBook("Mejores recetas de Julio");
		RecipeBook recipeBook4 = new RecipeBook("Recetas Agosto 2022");
		RecipeBook recipeBook5 = new RecipeBook("Nuevas tendencias de cocina");
		
		List<Recipe> recipes = getRecipes();
		
		recipes.stream().forEach(recipe -> {
			recipeBook1.addRecipe(recipe);
		});
		recipes.remove(0);
		recipes.stream().forEach(recipe -> {
			recipeBook2.addRecipe(recipe);
		});
		
		recipeBook3.addRecipe(recipes.get(2));
		
		recipeBooks.add(recipeBook1);
		recipeBooks.add(recipeBook2);
		recipeBooks.add(recipeBook3);
		recipeBooks.add(recipeBook4);
		recipeBooks.add(recipeBook5);
		return recipeBooks;
		
		
	}
	
	private List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		
		Recipe recipeChickenSalad = new Recipe("Chicken Salad");
		Food[] ingredientsChickenSalad = {
				new Food("Tomatoe", 102, FoodGroup.FRUITS, Unit.UNIT),
				new Food("Chicken", 100, FoodGroup.MEATS, Unit.UNIT),
				new Food("Rice", 6, FoodGroup.CEREALS, Unit.GRAM),
				new Food("Basil", 0, FoodGroup.VEGETABLES, Unit.CN),
				new Food("Fish", 340, FoodGroup.MEATS,  Unit.UNIT),
				new Food("Salt", 2000, FoodGroup.OTHER, Unit.CN)
		};
		
		recipeChickenSalad.addIngredient(3, ingredientsChickenSalad[0]);
		recipeChickenSalad.addIngredient(1, ingredientsChickenSalad[1]);
		recipeChickenSalad.addIngredient(200, ingredientsChickenSalad[2]);
		recipeChickenSalad.addIngredient(1, ingredientsChickenSalad[3]);
		recipeChickenSalad.addIngredient(0.5, ingredientsChickenSalad[4]);
		recipeChickenSalad.addIngredient(6, ingredientsChickenSalad[5]);
		recipes.add(recipeChickenSalad);
		
		Recipe recipeAppleCakeCeliacs = new Recipe("Apple cake for celiacs");
		Food[] ingredientsAppleCakeCeliacs = {
				new Food("Butter", 5, FoodGroup.MILK_PRODUCTS, Unit.GRAM),
				new Food("Premixture", 7, FoodGroup.OTHER, Unit.GRAM),
				new Food("Sugar", 4, FoodGroup.OTHER, Unit.GRAM),
				new Food("Egg", 24, FoodGroup.MILK_PRODUCTS, Unit.UNIT),
				new Food("Apple", 15, FoodGroup.FRUITS, Unit.UNIT),
				new Food("Bake Powder", 10, FoodGroup.OTHER, Unit.SPOON)
		};
		
		recipeAppleCakeCeliacs.addIngredient(125,ingredientsAppleCakeCeliacs[0]);
		recipeAppleCakeCeliacs.addIngredient(115,ingredientsAppleCakeCeliacs[1]);
		recipeAppleCakeCeliacs.addIngredient(225,ingredientsAppleCakeCeliacs[2]);
		recipeAppleCakeCeliacs.addIngredient(3,ingredientsAppleCakeCeliacs[3]);
		recipeAppleCakeCeliacs.addIngredient(3,ingredientsAppleCakeCeliacs[4]);
		recipeAppleCakeCeliacs.addIngredient(1,ingredientsAppleCakeCeliacs[5]);
		recipes.add(recipeAppleCakeCeliacs);
		
		Recipe recipePasta = new Recipe("Pasta with tomatoe sauce");
		Food[] ingredientsPasta = {
				new Food("Noodles", 2, FoodGroup.CEREALS, Unit.GRAM),
				new Food("Tomatoe", 102, FoodGroup.FRUITS, Unit.UNIT),
				new Food("Pepper", 43, FoodGroup.VEGETABLES, Unit.UNIT),
				new Food("Condiment", 24, FoodGroup.VEGETABLES, Unit.CN),
				new Food("Salt",14,FoodGroup.OTHER, Unit.CN)
		};
		
		recipePasta.addIngredient(350,ingredientsPasta[0]);
		recipePasta.addIngredient(2,ingredientsPasta[1]);
		recipePasta.addIngredient(0.5,ingredientsPasta[2]);
		recipePasta.addIngredient(1,ingredientsPasta[3]);
		recipePasta.addIngredient(1, ingredientsPasta[4]);
		recipes.add(recipePasta);
		
		Recipe recipeCheesecake = new Recipe("Cheesecake");
		Food[] ingredientsCheesecake = {
				new Food("Galletas molidas", 22, FoodGroup.CEREALS, Unit.GRAM),
				new Food("Azucar rubia", 102, FoodGroup.OTHER, Unit.CUP),
				new Food("Mantequilla", 43, FoodGroup.MILK_PRODUCTS, Unit.GRAM),
				new Food("Queso crema", 24, FoodGroup.VEGETABLES, Unit.UNIT),
				new Food("Leche",14,FoodGroup.OTHER, Unit.CUP),
				new Food("Huevo", 24, FoodGroup.MILK_PRODUCTS, Unit.UNIT),
				new Food("Ralladura de limon", 4, FoodGroup.FRUITS, Unit.CN),
				new Food("Harina", 24, FoodGroup.CEREALS, Unit.CUP),
				new Food("Frutos rojos", 24, FoodGroup.FRUITS, Unit.CN),
		};
		
		recipeCheesecake.addIngredient(200,ingredientsCheesecake[0]);
		recipeCheesecake.addIngredient(0.25,ingredientsCheesecake[1]);
		recipeCheesecake.addIngredient(120,ingredientsCheesecake[2]);
		recipeCheesecake.addIngredient(4,ingredientsCheesecake[3]);
		recipeCheesecake.addIngredient(0.75, ingredientsCheesecake[4]);
		recipeCheesecake.addIngredient(4, ingredientsCheesecake[5]);
		recipeCheesecake.addIngredient(0, ingredientsCheesecake[6]);
		recipeCheesecake.addIngredient(0.25, ingredientsCheesecake[7]);
		recipeCheesecake.addIngredient(0, ingredientsCheesecake[8]);
		recipes.add(recipeCheesecake);
		
		return recipes;
	}
	
}
