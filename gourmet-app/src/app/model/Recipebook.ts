import { Recipe } from './Recipe';

export class RecipeBook{
    id:number;
    title:string;
    recipes:Recipe[];

    constructor(id:number, title:string, recipes?:Recipe[]){
        this.id = id;
        this.title = title;
        this.recipes = recipes ? recipes : [];
    }
    
    setRecipes(recipes:Recipe[]){
        this.recipes = recipes;
    }

    getNumberOfRecipes():number{
        return this.recipes == undefined ? 0 : this.recipes.length;
    }
}