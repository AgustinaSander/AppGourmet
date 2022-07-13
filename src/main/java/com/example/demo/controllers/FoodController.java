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

import com.example.demo.domain.Food;
import com.example.demo.domain.DTO.FoodDTO;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.FoodRepository;

@RestController
@CrossOrigin
public class FoodController {
	private final FoodRepository foodRepository;
	
	FoodController (FoodRepository foodRepository){
		this.foodRepository = foodRepository;
	}
	
	@GetMapping("/foods")
	CollectionModel<EntityModel<FoodDTO>> all() throws Exception {
		List<EntityModel<FoodDTO>> foodDTO = new ArrayList<>();
		List<Food> foods = (List<Food>) foodRepository.findAll();
		if(foods.size()==0) {
	       throw new NotFoundException("food");
		}
		foods.stream().forEach(food -> foodDTO.add(toModel(food.convertToFoodDTO())));
		
		return CollectionModel.of(foodDTO, linkTo(methodOn(FoodController.class).all()).withSelfRel());
   	}
	
	Food addFood(FoodDTO foodDTO){
		Food food = new Food(foodDTO.getName(), foodDTO.getCalories(), foodDTO.getFoodGroup(), foodDTO.getUnit());
		return foodRepository.save(food);
	}
	
	public EntityModel<FoodDTO> toModel(FoodDTO food) {
	    try {
			return EntityModel.of(food, 
			    linkTo(methodOn(FoodController.class).all()).withRel("foods"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
	 }
}
