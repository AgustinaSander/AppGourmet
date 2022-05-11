package domain;

import domain.enumerations.Unit;

public class FoodQuantity {
	private float quantity;
	private Unit unit;
	private Food food;
	
	public FoodQuantity() {}
	
	public FoodQuantity(float quantity, Unit unit, Food food) {
		super();
		this.quantity = quantity;
		this.unit = unit;
		this.food = food;
	}
	
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
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
