import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { RecipeBook } from '../model/Recipebook';
import { Router } from '@angular/router';
import { Recipe } from '../model/Recipe';
import { forkJoin, ObservableInput } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeBooksService {
  constructor(private http: HttpClient, private router:Router) { 
  }

  getRecipeBook(id:number){ 
    return this.http.get<any>('http://localhost:8080/recipebooks/'+id)
  } 

  getRecipeBooks(){
    return this.http.get<any>('http://localhost:8080/recipebooks');
  }

  getRecipesFromBook(id:number){
    return this.http.get<any>('http://localhost:8080/recipebooks/'+id+'/recipes');
  }

  addRecipeBook(recipeBook:RecipeBook) {
    return this.http.post<RecipeBook>('http://localhost:8080/recipebooks', recipeBook);
  }

  addRecipeInRecipeBook(recipes:Recipe[], idRecipebook:number){
    let response: ObservableInput<any>[] = [];
    recipes.forEach(recipe => {
      response.push(this.http.put<any>('http://localhost:8080/recipebooks/'+idRecipebook+'/recipes/'+recipe.getId(),null));
    });
    return forkJoin(response);
  }
}

