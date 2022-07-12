import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { RecipeBook } from '../model/Recipebook';
import { Recipe } from '../model/Recipe';
import { FoodQuantity } from '../model/FoodQuantity';
import { Food } from '../model/Food';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

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
}

