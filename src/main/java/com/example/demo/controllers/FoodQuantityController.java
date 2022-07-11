package com.example.demo.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.FoodQuantity;
import com.example.demo.domain.DTO.FoodQuantityDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.FoodQuantityRepository;

@RestController
@CrossOrigin
public class FoodQuantityController {
	private final FoodQuantityRepository foodQuantityRepository;
	
	FoodQuantityController (FoodQuantityRepository foodQuantityRepository){
		this.foodQuantityRepository = foodQuantityRepository;
	}
	
	@GetMapping("/foodquantities")
	CollectionModel<EntityModel<FoodQuantityDTO>> all() throws Exception {
		List<EntityModel<FoodQuantityDTO>> foodQuantityDTO = new ArrayList<>();
		List<FoodQuantity> foodquantities = (List<FoodQuantity>) foodQuantityRepository.findAll();
		if(foodquantities.size()==0) {
	       throw new NotFoundException("food quantity");
		}
		foodquantities.stream().forEach(foodquantity -> foodQuantityDTO.add(toModel(foodquantity.convertToFoodQuantityDTO())));
		
		return CollectionModel.of(foodQuantityDTO, linkTo(methodOn(FoodQuantityController.class).all()).withSelfRel());
   	}
	
	public EntityModel<FoodQuantityDTO> toModel(FoodQuantityDTO foodquantity) {
	    try {
			return EntityModel.of(foodquantity, 
			    linkTo(methodOn(FoodQuantityController.class).all()).withRel("foodquantities"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	 }
}
