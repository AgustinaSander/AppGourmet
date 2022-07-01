import { FoodQuantity } from './FoodQuantity';

export class Recipe{
    id!:number;
    title!:string;
    foodQuantities!:FoodQuantity[];

    constructor(){}
}