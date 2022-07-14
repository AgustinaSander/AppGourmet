package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.RecipeBookDAOImpl;
import com.example.demo.domain.Recipe;
import com.example.demo.domain.RecipeBook;
import com.example.demo.domain.DTO.RecipeBookDTO;
import com.example.demo.domain.DTO.RecipeDTO;
import com.example.demo.exceptions.NotFoundException;

@RestController
@CrossOrigin
public class RecipeBookController {
	@Autowired
	RecipeBookDAOImpl recipeBookDAO;
	
	@GetMapping("/recipebooks")
	CollectionModel<EntityModel<RecipeBookDTO>> all(){
		List<EntityModel<RecipeBookDTO>> recipeBookDTO = new ArrayList<>();
		List<RecipeBook> recipeBooks = recipeBookDAO.getAllRecipeBooks();
		if(recipeBooks.size()==0) {
	       throw new NotFoundException("recipe books");
		}
		recipeBooks.stream().forEach(recipeBook -> recipeBookDTO.add(toModel(recipeBook.convertToRecipeBookDTO())));
		
		return CollectionModel.of(recipeBookDTO, linkTo(methodOn(RecipeBookController.class).all()).withSelfRel());
   	}
	
	
	@GetMapping("/recipebooks/{id}")
	EntityModel<RecipeBookDTO> one(@PathVariable int id){
		RecipeBookDTO recipeBookDTO = new RecipeBookDTO();
		RecipeBook recipeBook = recipeBookDAO.getRecipeBookById(id);
	
		recipeBookDTO = recipeBook.convertToRecipeBookDTO();
		return toModel(recipeBookDTO);
	}
	
	@GetMapping("/recipebooks/{id}/recipes")
	CollectionModel<RecipeDTO> recipesInRecipeBook(@PathVariable int id){
		List<RecipeDTO> recipesDTO = new ArrayList<>();
		List<Recipe> recipes = recipeBookDAO.getRecipesFromRecipeBook(id);
		recipes.stream().forEach(recipe -> recipesDTO.add(recipe.convertToRecipeDTO()));
		
		return CollectionModel.of(recipesDTO, 
				linkTo(methodOn(RecipeBookController.class).recipesInRecipeBook(id)).withSelfRel(),
				linkTo(methodOn(RecipeBookController.class).one(id)).withRel("recipebook"),
				linkTo(methodOn(RecipeBookController.class).all()).withRel("recipebooks"));
	}

	public EntityModel<RecipeBookDTO> toModel(RecipeBookDTO recipeBook) {
	    try {
			return EntityModel.of(recipeBook, 
			    linkTo(methodOn(RecipeBookController.class).one(recipeBook.getId())).withSelfRel(),
			    linkTo(methodOn(RecipeBookController.class).all()).withRel("recipebooks"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	 }
}