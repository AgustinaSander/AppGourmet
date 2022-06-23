package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Food;
import com.example.demo.domain.DTO.FoodDTO;
import com.example.demo.repositories.FoodRepository;

@RestController
public class FoodController {
	private final FoodRepository foodRepository;
	
	FoodController (FoodRepository foodRepository){
		this.foodRepository = foodRepository;
	}
	
	@GetMapping("/foods")
   	List<FoodDTO> all() {
		List<FoodDTO> foodDTO = new ArrayList<>();
        ((List<Food>) foodRepository.findAll()).stream()
									.forEach(food -> foodDTO.add(food.convertToFoodDTO()));
        return foodDTO;
   	}
	
	/*
	@PostMapping("/foods")
   	Food create(@RequestBody Food food) {
          	return foodRepository.save(food);
   	}
   	*/
}
