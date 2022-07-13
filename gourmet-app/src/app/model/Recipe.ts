import { FoodQuantity } from './FoodQuantity';

export class Recipe{
    id!:number;
    title!:string;
    foodQuantity:FoodQuantity[];
    
    constructor(id?:number, title?:string, foodQuantity?:FoodQuantity[]){
        this.id = id ? id : 0;
        this.title = title ? title : '';
        this.foodQuantity = foodQuantity ? foodQuantity : [];
    }

    setFoodQuantity(foodQuantity:FoodQuantity[]){
        this.foodQuantity = foodQuantity;
    }

    setTitle(title: string){
        this.title=title;
    }

    getId():number{
        return this.id;
    }
}