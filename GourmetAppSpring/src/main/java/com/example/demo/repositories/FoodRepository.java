package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Food;

@Repository
public interface FoodRepository extends CrudRepository<Food,Integer>{
}
