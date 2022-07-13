package com.example.demo.exceptions;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException{
	
	public NotFoundException(String object) {
	    super("Could not find "+object+".");
	}
	
	public NotFoundException(int id, String object) {
	    super("Could not find "+ object +" "+ id + ".");
	}
}
