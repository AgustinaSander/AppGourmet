import { FoodGroup } from "./FoodGroup";
import { Unit } from "./Unit";

export class Food{
    id!:number;
    name!:string;
    calories!:number;
    foodGroup!:FoodGroup;
    unit!:Unit;

    constructor(id:number, name:string, calories:number, foodGroup:FoodGroup, unit:Unit){
        this.id=id;
        this.name=name;
        this.calories=calories;
        this.foodGroup=foodGroup;
        this.unit=unit;
    }

    toString():string{
        return "Food [id=" + this.id + ", name=" + this.name + ", calories=" + this.calories + ", foodGroup=" + this.foodGroup + ", unit="
				+ this.unit + "]";
    }
}