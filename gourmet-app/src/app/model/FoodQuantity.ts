import { Food } from "./Food";

export class FoodQuantity{
    quantity!: number;
    food!:Food;

    constructor(quantity:number, food:Food){
        this.quantity=quantity;
        this.food=food;
    }
}