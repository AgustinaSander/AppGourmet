package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.RecipeBook;
import com.example.demo.domain.DTO.RecipeBookDTO;
import com.example.demo.domain.DTO.RecipeDTO;
import com.example.demo.repositories.RecipeBookRepository;

@RestController
public class RecipeBookController {
	private final RecipeBookRepository recipeBookRepository;
	
	RecipeBookController (RecipeBookRepository recipeBookRepository){
		this.recipeBookRepository = recipeBookRepository;
	}
	
	@GetMapping("/recipebooks")
   	List<RecipeBookDTO> all() {
		List<RecipeBookDTO> recipeBookDTO = new ArrayList<>();
        ((List<RecipeBook>) recipeBookRepository.findAll()).stream()
         										.forEach(recipeBook -> recipeBookDTO.add(recipeBook.convertToRecipeBookDTO()));
        return recipeBookDTO;
   	}
	
	
	@GetMapping("/recipebooks/{id}")
	RecipeBookDTO one(@PathVariable int id) {
		RecipeBookDTO recipeBookDTO = new RecipeBookDTO();
		Optional<RecipeBook> recipeBook = recipeBookRepository.findById(id);
		if(recipeBook.isPresent()) {
			recipeBookDTO = recipeBook.get().convertToRecipeBookDTO();
		}
		return recipeBookDTO;
	}
	
	@GetMapping("/recipebooks/{id}/recipes")
	List<RecipeDTO> recipesInRecipeBook(@PathVariable int id){
		List<RecipeDTO> recipesDTO = new ArrayList<>();
		Optional<RecipeBook> recipeBook = recipeBookRepository.findById(id);
		if(recipeBook.isPresent()) {
			recipeBook.get().getListRecipes().stream()
							.forEach(recipe -> recipesDTO.add(recipe.convertToRecipeDTO()));
		}
		
		return recipesDTO;
	}

}
