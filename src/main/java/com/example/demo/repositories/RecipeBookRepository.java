package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.RecipeBook;

@Repository
public interface RecipeBookRepository extends CrudRepository<RecipeBook,Integer>{

}
