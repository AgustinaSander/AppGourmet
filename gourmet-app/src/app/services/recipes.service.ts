import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Router } from '@angular/router';
import { Recipe } from '../model/Recipe';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecipesService {
  constructor(private http: HttpClient) { }

  addRecipe(recipe:Recipe) {
    return this.http.post<Recipe>(`${environment.baseURL}recipes`, recipe);
  }

  getAllRecipes(){
    return this.http.get<any>(`${environment.baseURL}recipes`);
  }

  getRecipe(id:number){ 
    return this.http.get<any>(`${environment.baseURL}recipes/${id}`)
  } 
}

