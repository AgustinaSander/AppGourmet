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

  addRecipe(recipe:Recipe, id:number) {
    return this.http.post<Recipe>('http://localhost:8080/recipebooks/'+id+'/recipes', recipe);
  }
}

