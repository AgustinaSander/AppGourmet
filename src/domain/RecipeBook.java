package domain;

import java.util.List;

public class RecipeBook {
	private int id;
	private String title;
	private List<Recipe> listRecipes;
	
	public RecipeBook() {}
	
	public RecipeBook(int id, String title, List<Recipe> listRecipes) {
		super();
		this.id = id;
		this.title = title;
		this.listRecipes = listRecipes;
	}
	
	public int getNumberOfRecipes(){
		return listRecipes.size();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
}
