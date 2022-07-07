import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { RecipeBook } from '../model/Recipebook';
import { Recipe } from '../model/Recipe';
import { FoodQuantity } from '../model/FoodQuantity';
import { Food } from '../model/Food';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeBooksService {

  constructor(private http: HttpClient) { 
  }

  existsRecipeBook(id:number){
    return this.http.get('http://localhost:8080/recipebooks/'+id).pipe(
      catchError(this.handleError)
    );   
  } 

  private handleError(error: HttpErrorResponse) {
    console.error('An error occurred:');
    if (error.status === 404) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } 
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }

  getRecipeBooks(): RecipeBook[]{
    let recipebooks:RecipeBook[]=[];
    this.http.get<any>('http://localhost:8080/recipebooks').subscribe(response => {
      for(let item of response._embedded.recipeBookDTOes){
        let recipebook = new RecipeBook(item.id, item.title);
        /* Build recipe */
        let recipesList = [];
        for(let recipeItem of item.listRecipes){
          let recipe = new Recipe(recipeItem.id, recipeItem.title);
          /* Build foodQuantity */
          let foodquantityList = [];
          for(let foodquantityItem of recipeItem.foodQuantity){
            /* Build food */
            let food = new Food(foodquantityItem.food.id, foodquantityItem.food.name, foodquantityItem.food.calories, foodquantityItem.food.foodGroup, foodquantityItem.food.unit);
            let foodquantity = new FoodQuantity(foodquantityItem.quantity, food);
            foodquantityList.push(foodquantity);
          }
          recipe.setFoodQuantities(foodquantityList);
          recipesList.push(recipe);
        }
        recipebook.setRecipes(recipesList);
        recipebooks.push(recipebook);
      }
    });

    return recipebooks;
  }

  getRecipesFromBook(id:number):Recipe[]{
    let recipes:Recipe[]=[];
    this.http.get<any>('http://localhost:8080/recipebooks/'+id+'/recipes').subscribe(response => {
      for(let recipeItem of response._embedded.recipeDTOes){
        let recipe = new Recipe(recipeItem.id, recipeItem.title);
          /* Build foodQuantity */
          let foodquantityList = [];
          for(let foodquantityItem of recipeItem.foodQuantity){
            /* Build food */
            let food = new Food(foodquantityItem.food.id, foodquantityItem.food.name, foodquantityItem.food.calories, foodquantityItem.food.foodGroup, foodquantityItem.food.unit.toLowerCase());
            let foodquantity = new FoodQuantity(foodquantityItem.quantity, food);
            foodquantityList.push(foodquantity);
          }
          recipe.setFoodQuantities(foodquantityList);
          recipes.push(recipe);
      }
    });
    return recipes;
  }
}
