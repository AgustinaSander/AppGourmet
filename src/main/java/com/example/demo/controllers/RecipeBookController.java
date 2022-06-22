package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeBook;
import com.example.demo.repositories.RecipeBookRepository;

@RestController
public class RecipeBookController {
	private final RecipeBookRepository recipeBookRepository;
	
	RecipeBookController (RecipeBookRepository recipeBookRepository){
		this.recipeBookRepository = recipeBookRepository;
	}
	
	@GetMapping("/recipebooks")
   	List<RecipeBook> all() {
          	List<RecipeBook> recipeBooks = new ArrayList<>();
          	recipeBookRepository.findAll().forEach(recipeBooks::add);
          	return recipeBooks;
   	}
	
	@GetMapping("/recipebooks/{id}")
	RecipeBook one(@PathVariable int id) {
		System.out.println(id);
		return recipeBookRepository.findById(id).get();
	}
	
	@GetMapping("/recipebooks/{id}/recipes")
	List<Recipe> recipesInRecipeBook(@PathVariable int id){
		RecipeBook recipeBook = recipeBookRepository.findById(id).get();
		return recipeBook.getListRecipes();
	}

}
