package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.domain.RecipeBook;
import com.example.demo.domain.DTO.RecipeBookDTO;
import com.example.demo.domain.DTO.RecipeDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.RecipeBookRepository;

@RestController
@CrossOrigin
public class RecipeBookController {
	private final RecipeBookRepository recipeBookRepository;
	
	RecipeBookController (RecipeBookRepository recipeBookRepository){
		this.recipeBookRepository = recipeBookRepository;
	}
	
	@GetMapping("/recipebooks")
	CollectionModel<EntityModel<RecipeBookDTO>> all() throws Exception {
		List<EntityModel<RecipeBookDTO>> recipeBookDTO = new ArrayList<>();
		List<RecipeBook> recipeBooks = (List<RecipeBook>) recipeBookRepository.findAll();
		if(recipeBooks.size()==0) {
	       throw new NotFoundException("recipe books");
		}
		recipeBooks.stream().forEach(recipeBook -> recipeBookDTO.add(toModel(recipeBook.convertToRecipeBookDTO())));
		
		return CollectionModel.of(recipeBookDTO, linkTo(methodOn(RecipeBookController.class).all()).withSelfRel());
   	}
	
	
	@GetMapping("/recipebooks/{id}")
	EntityModel<RecipeBookDTO> one(@PathVariable int id) throws Exception {
		RecipeBookDTO recipeBookDTO = new RecipeBookDTO();
		RecipeBook recipeBook = recipeBookRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id, "recipe books"));
		
		recipeBookDTO = recipeBook.convertToRecipeBookDTO();
		
		return toModel(recipeBookDTO);
	}
	
	@GetMapping("/recipebooks/{id}/recipes")
	CollectionModel<RecipeDTO> recipesInRecipeBook(@PathVariable int id) throws Exception{
		List<RecipeDTO> recipesDTO = new ArrayList<>();
		RecipeBook recipeBook = recipeBookRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id, "recipe books"));
		
		recipeBook.getListRecipes().stream().forEach(recipe -> recipesDTO.add(recipe.convertToRecipeDTO()));
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