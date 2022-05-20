package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Recipe;
import domain.RecipeBook;

public class RecipeBookTest {

	RecipeBook book;
	int sizeListRecipes = 4;
	
	@Before
	public void setUp() {
		List<Recipe> listRecipes = new ArrayList<>();
		for(int i=1; i<= sizeListRecipes; i++) {
			listRecipes.add(new Recipe(i,"Recipe "+i, null));
		}
		book = new RecipeBook(1, "One week meals", listRecipes);
	}
	
	@Test
	public void testNumberOfRecipes() {
		assertEquals(sizeListRecipes, book.getNumberOfRecipes());
	}

}
