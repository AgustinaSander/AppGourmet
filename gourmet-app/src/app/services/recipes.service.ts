import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Router } from '@angular/router';
import { Recipe } from '../model/Recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipesService {
  constructor(private http: HttpClient, private router:Router) { 
  }

  addRecipe(recipe:Recipe) {
    return this.http.post<Recipe>('http://localhost:8080/recipes', recipe);
  }

  getAllRecipes(){
    return this.http.get<any>('http://localhost:8080/recipes');
  }

  getRecipe(id:number){ 
    return this.http.get<any>('http://localhost:8080/recipes/'+id)
  } 
}

