package com.example.demo.exceptions;

@SuppressWarnings("serial")
public class RecipeBookNotFoundException extends RuntimeException{
	
	public RecipeBookNotFoundException() {
	    super("Could not find recipe books.");
	}
	
	public RecipeBookNotFoundException(int id) {
	    super("Could not find recipe book " + id + ".");
	}
}
