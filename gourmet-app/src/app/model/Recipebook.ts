import { Recipe } from './Recipe';

export class RecipeBook{
    id:number;
    title:string;
    recipes:Recipe[];

    constructor(id:number, title:string, recipes:Recipe[]){
        this.id = id;
        this.title = title;
        this.recipes = recipes;
    }
    
    public getNumberOfRecipes():number{
        return this.recipes == undefined ? 0 : this.recipes.length;
    }
}