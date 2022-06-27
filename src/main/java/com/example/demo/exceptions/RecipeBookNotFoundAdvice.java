package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RecipeBookNotFoundAdvice {
	  @ResponseBody
	  @ExceptionHandler(RecipeBookNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String recipeBookNotFoundHandler(RecipeBookNotFoundException exception) {
	    return exception.getMessage();
	  }
}
