package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Recipe;
import domain.RecipeBook;

public class RecipeBookTest {

	RecipeBook book;
	List<Recipe> listRecipes = new ArrayList<>();
	int sizeListRecipes = 4;
	
	@Before
	public void setUp() {
		for(int i=1; i<= sizeListRecipes; i++) {
			listRecipes.add(new Recipe(i,"Recipe "+i, null));
		}
		book = new RecipeBook(1, "One week meals", listRecipes);
	}
	
	// ------ NUMBER OF RECIPES ------
	@Test
	public void testNumberOfRecipes() {
		assertEquals(sizeListRecipes, book.getNumberOfRecipes());
	}

	// ------ ADD NEW RECIPE ------
	@Test
	public void testAddRecipe() {
		assertTrue(book.addRecipe(new Recipe(sizeListRecipes+1, "New Recipe", null)));
	}
	
	@Test
	public void testAddSameRecipe() {
		assertFalse(book.addRecipe(listRecipes.get(0)));
	}
	
	@Test
	public void testAddNullRecipe() {
		assertFalse(book.addRecipe(null));
	}
	
	// ------ DELETE RECIPE ------
	@Test
	public void testRemoveRecipe() {
		assertTrue(book.removeRecipe(listRecipes.get(0)));
	}
	
	@Test
	public void testRemoveNotExistingRecipe() {
		assertFalse(book.removeRecipe(new Recipe(0, "Recipe never added", null)));
	}
	
	@Test
	public void testRemoveNullRecipe() {
		assertFalse(book.removeRecipe(null));
	}
	
}
