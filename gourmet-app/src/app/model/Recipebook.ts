import { Recipe } from './Recipe';

export class RecipeBook{
    id:number;
    title:string;
    listRecipes:Recipe[];

    constructor(id?:number, title?:string, recipes?:Recipe[]){
        this.id = id ? id : 0;
        this.title = title ? title: '';
        this.listRecipes = recipes ? recipes : [];
    }
    
    setTitle(title:string){
        this.title = title;
    }

    getTitle():string{
        return this.title;
    }

    setRecipes(recipes:Recipe[]){
        this.listRecipes = recipes;
    }

    getNumberOfRecipes():number{
        return this.listRecipes == undefined ? 0 : this.listRecipes.length;
    }

    getListRecipes():Recipe[]{
        return this.listRecipes;
    }

    setId(id:number){
        this.id=id;
    }

    getId():number{
        return this.id;
    }
}