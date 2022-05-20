package domain;

import domain.enumerations.FoodGroup;
import domain.enumerations.Unit;

public class Food {
	private int id;
	private String name;
	private int calories;
	private FoodGroup foodGroup;
	private Unit unit;
	
	public Food(int id, String name, int calories, FoodGroup foodGroup, Unit unit) {
		this.id = id;
		this.name = name;
		this.calories = unit == Unit.CN ? 0 : calories;
		this.foodGroup = foodGroup;
		this.unit = unit;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCalories() {
		return calories;
	}
	
	public void setCalories(int calories) {
		this.calories = this.unit == Unit.CN ? 0 : calories;
	}
	
	public FoodGroup getFoodGroup() {
		return foodGroup;
	}
	
	public void setFoodGroup(FoodGroup foodGroup) {
		this.foodGroup = foodGroup;
	}

	public Unit getUnit() {
		return unit;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", calories=" + calories + ", foodGroup=" + foodGroup + ", unit="
				+ unit + "]";
	}

	
}
