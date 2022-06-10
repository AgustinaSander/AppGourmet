package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.demo.domain.enumerations.Unit;

@Entity
public class FoodQuantity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private double quantity;
	@ManyToOne
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
