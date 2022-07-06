import { FoodQuantity } from './FoodQuantity';

export class Recipe{
    id!:number;
    title!:string;
    foodQuantities!:FoodQuantity[];

    constructor(id:number, title:string, foodQuantities:FoodQuantity[]){
        this.id = id;
        this.title = title;
        this.foodQuantities = foodQuantities;
    }
}