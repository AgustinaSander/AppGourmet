package com.example.demo.domain.DTO;


public class FoodQuantityDTO {
	private int id;
	private double quantity;
	private FoodDTO food;
	
	public FoodQuantityDTO() {}

	
	public FoodQuantityDTO(int id, double quantity) {
		this.quantity = quantity;
		this.id = id;
	}
	
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public FoodDTO getFood() {
		return food;
	}
	
	public void setFood(FoodDTO food) {
		this.food = food;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
