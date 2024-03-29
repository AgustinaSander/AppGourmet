package com.example.demo.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeBook;
import com.example.demo.repositories.RecipeBookRepository;

import com.example.demo.domain.Food;
import com.example.demo.domain.enumerations.FoodGroup;
import com.example.demo.domain.enumerations.Unit;

@SpringBootTest
@RunWith(SpringRunner.class)
class RecipeBookPersistenceTest {
	@Autowired
	RecipeBookRepository recipeBookRepository;
	
	@Test
	public void createRecipeBook() {
		RecipeBook recipeBook = getRecipeBook();
		recipeBookRepository.save(recipeBook);
		int sizeListRecipeBook = ((Collection<RecipeBook>) recipeBookRepository.findAll()).size();
		
		assertNotEquals(sizeListRecipeBook, 0);
	}
      
	@Test
	public void readRecipeBook() {
		addRecipeBook();
		int idSavedRecipeBook = ((List<RecipeBook>) recipeBookRepository.findAll()).get(0).getId();
		
		assertTrue(recipeBookRepository.findById(idSavedRecipeBook).isPresent());
	}
	
	@Test
	public void updateRecipeBook() {
		addRecipeBook();
		int idSavedRecipeBook = ((List <RecipeBook>) recipeBookRepository.findAll()).get(0).getId();
		String newTitle = "New recipe book title";
		RecipeBook recipeBook = recipeBookRepository.findById(idSavedRecipeBook).get();
		
		recipeBook.setTitle(newTitle);
		recipeBookRepository.save(recipeBook);
		
		assertEquals(recipeBookRepository.findById(idSavedRecipeBook).get().getTitle(), newTitle);
	}
	
	@Test
	public void deleteRecipeBook() {
		addRecipeBook();
		List<RecipeBook> listRecipeBook = (List<RecipeBook>) recipeBookRepository.findAll();
		int idSavedRecipeBook = listRecipeBook.get(0).getId();
		
		recipeBookRepository.deleteById(idSavedRecipeBook);
		
		assertTrue(listRecipeBook.size() > ((List<RecipeBook>) recipeBookRepository.findAll()).size());
	}
	
	public void addRecipeBook() {
		RecipeBook recipeBook = new RecipeBook("RecipeBook already added");
		Recipe recipe = getRecipe2();
		recipeBook.addRecipe(recipe);
		recipeBookRepository.save(recipeBook);
	}

	private RecipeBook getRecipeBook() {
		RecipeBook recipeBook = new RecipeBook("RecipeBook for Test");
		Recipe recipe = getRecipe();
		
		recipeBook.addRecipe(recipe);
		return recipeBook;
	}
	
	private Recipe getRecipe() {
		Recipe recipeChickenSalad = new Recipe("Chicken Salad");
		Food[] ingredients = {
				new Food("Tomatoe", 102, FoodGroup.FRUITS, Unit.UNIT),
				new Food("Chicken", 100, FoodGroup.MEATS, Unit.UNIT),
				new Food("Rice", 6, FoodGroup.CEREALS, Unit.GRAM),
				new Food("Basil", 0, FoodGroup.VEGETABLES, Unit.CN),
				new Food("Fish", 340, FoodGroup.MEATS,  Unit.UNIT),
				new Food("Salt", 2000, FoodGroup.OTHER, Unit.CN)
		};
		
		recipeChickenSalad.addIngredient(3, ingredients[0]);
		recipeChickenSalad.addIngredient(1, ingredients[1]);
		recipeChickenSalad.addIngredient(200, ingredients[2]);
		recipeChickenSalad.addIngredient(1, ingredients[3]);
		recipeChickenSalad.addIngredient(0.5, ingredients[4]);
		recipeChickenSalad.addIngredient(6, ingredients[5]);
		return recipeChickenSalad;
	}
	
	private Recipe getRecipe2() {
		Recipe recipeAppleCakeCeliacs = new Recipe("Apple cake for celiacs");
		Food[] foodRecipe2 = {
				new Food("Butter", 5, FoodGroup.MILK_PRODUCTS, Unit.GRAM),
				new Food("Premixture", 7, FoodGroup.OTHER, Unit.GRAM),
				new Food("Sugar", 4, FoodGroup.OTHER, Unit.GRAM),
				new Food("Egg", 24, FoodGroup.MILK_PRODUCTS, Unit.UNIT),
				new Food("Apple", 15, FoodGroup.FRUITS, Unit.UNIT),
				new Food("Bake Powder", 10, FoodGroup.OTHER, Unit.SPOON)
		};
		
		recipeAppleCakeCeliacs.addIngredient(125,foodRecipe2[0]);
		recipeAppleCakeCeliacs.addIngredient(115,foodRecipe2[1]);
		recipeAppleCakeCeliacs.addIngredient(225,foodRecipe2[2]);
		recipeAppleCakeCeliacs.addIngredient(3,foodRecipe2[3]);
		recipeAppleCakeCeliacs.addIngredient(3,foodRecipe2[4]);
		recipeAppleCakeCeliacs.addIngredient(1,foodRecipe2[5]);
		return recipeAppleCakeCeliacs;
	}
	
}
