package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class RecipeBook {
	private int id;
	private String title;
	private List<Recipe> listRecipes;
	
	public RecipeBook(int id, String title) {
		this.id = id;
		this.title = title;
		this.listRecipes = new ArrayList<>();
	}

	public RecipeBook(int id, String title, List<Recipe> listRecipes) {
		this.id = id;
		this.title = title;
		this.listRecipes = listRecipes;
	}
	
	public int getNumberOfRecipes(){
		return listRecipes.isEmpty() ? 0 : listRecipes.size();
	}
	
	public boolean addRecipe(Recipe newRecipe) {
		if(newRecipe == null) return false;
		
		boolean recipeAlreadyExists = getListRecipes().stream().anyMatch(recipe -> recipe.equals(newRecipe));
		 
		return recipeAlreadyExists ? false : listRecipes.add(newRecipe);
		
		//SI LA AGREGA HAY QUE MANDAR NOTIFICACION A TODOS LOS USUARIOS SUSCRITOS CON PERFIL APTO
	}
	
	public boolean removeRecipe(Recipe deleteRecipe) {
		if(deleteRecipe == null) return false;
		
		Optional<Recipe> delete = getListRecipes().stream().filter(recipe -> recipe.equals(deleteRecipe)).findAny();
		 
		try {
			return getListRecipes().remove(delete.get());
		}
		catch(NoSuchElementException exception) {
			return false;
		}
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Recipe> getListRecipes() {
		return listRecipes;
	}
	
	public void setListRecipes(List<Recipe> listRecipes) {
		this.listRecipes = listRecipes;
	}

	@Override
	public String toString() {
		return "RecipeBook [id=" + id + ", title=" + title + ", listRecipes=" + listRecipes + "]";
	}
	
	
}
