package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Food;
import com.example.demo.repositories.FoodRepository;

@RestController
public class FoodController {
	private final FoodRepository foodRepository;
	
	FoodController (FoodRepository foodRepository){
		this.foodRepository = foodRepository;
	}
	
	@GetMapping("/foods")
   	List<Food> all() {
          	List<Food> foods = new ArrayList<>();
          	foodRepository.findAll().forEach(foods::add);
          	return foods;
   	}
	
	@PostMapping("/foods")
   	Food create(@RequestBody Food food) {
          	return foodRepository.save(food);
   	}
}
