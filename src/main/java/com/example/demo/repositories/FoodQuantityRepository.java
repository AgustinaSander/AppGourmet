package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.FoodQuantity;

@Repository
public interface FoodQuantityRepository extends CrudRepository<FoodQuantity,Integer>{
}
