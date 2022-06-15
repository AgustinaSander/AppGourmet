package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe,Integer>{

}
