package domain;

import domain.enumerations.Unit;

public class FoodQuantity {
	private double quantity;
	private Unit unit;
	private Food food;
	
	public FoodQuantity() {}
	
	public FoodQuantity(double quantity, Unit unit, Food food) {
		this.quantity = quantity;
		this.unit = unit;
		this.food = food;
	}
	
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	
	
}
