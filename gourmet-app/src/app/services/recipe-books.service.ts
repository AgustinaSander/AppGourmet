import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { RecipeBook } from '../model/Recipebook';
import { Router } from '@angular/router';
import { Recipe } from '../model/Recipe';
import { forkJoin, ObservableInput } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecipeBooksService {
  constructor(private http: HttpClient, private router:Router) { 
  }

  getRecipeBook(id:number){ 
    return this.http.get<any>(`${environment.baseURL}recipebooks/${id}`)
  } 

  getRecipeBooks(){
    return this.http.get<any>(`${environment.baseURL}recipebooks`);
  }

  getRecipesFromBook(id:number){
    return this.http.get<any>(`${environment.baseURL}recipebooks/${id}/recipes`);
  }

  addRecipeBook(recipeBook:RecipeBook) {
    return this.http.post<RecipeBook>(`${environment.baseURL}recipebooks`, recipeBook);
  }

  addRecipeInRecipeBook(idRecipe:number, idRecipebook:number){
    return this.http.put<any>(`${environment.baseURL}recipebooks/${idRecipebook}/recipes/${idRecipe}`,null);
  }
}

