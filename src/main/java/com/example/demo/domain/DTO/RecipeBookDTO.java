package com.example.demo.domain.DTO;

import java.util.ArrayList;
import java.util.List;

public class RecipeBookDTO{
	private int id;
	private String title;
	private List<RecipeDTO> listRecipes;
	
	public RecipeBookDTO() {}
	
	public RecipeBookDTO(String title) {
		this.title = title;
		this.listRecipes = new ArrayList<>();
	}
	
	public RecipeBookDTO(int id, String title) {
		this.id = id;
		this.title = title;
		this.listRecipes = new ArrayList<>();
	}

	public RecipeBookDTO(int id, String title, List<RecipeDTO> listRecipes) {
		this.id = id;
		this.title = title;
		this.listRecipes = listRecipes;
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

	public List<RecipeDTO> getListRecipes() {
		return listRecipes;
	}

	public void setListRecipes(List<RecipeDTO> listRecipes) {
		this.listRecipes = listRecipes;
	}
	
	@Override
	public String toString() {
		return "RecipeBook [id=" + id + ", title=" + title + ", listRecipes=" + listRecipes + "]";
	}
	
	
}
