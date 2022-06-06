package domain;

import domain.enumerations.Unit;

public class FoodQuantity {
	private double quantity;
	private Food food;
	
	public FoodQuantity() {}

	public FoodQuantity(double quantity, Food food) {
		this.quantity = food.getUnit() == Unit.CN ? 0 : quantity;
		this.food = food;
	}
	
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = food.getUnit() == Unit.CN ? 0 : quantity;
	}
	
	public Food getFood() {
		return food;
	}
	
	public void setFood(Food food) {
		this.food = food;
	}

	@Override
	public String toString() {
		return "FoodQuantity [quantity=" + quantity + ", food=" + food + "]";
	}
	
	
	
}
