import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Food } from 'src/app/model/Food';
import { FoodQuantity } from 'src/app/model/FoodQuantity';
import { Recipe } from 'src/app/model/Recipe';
import { RecipeBooksService } from 'src/app/services/recipe-books.service';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.scss']
})
export class RecipesComponent implements OnInit {
  idRecipeBook:number = 0;
  recipes: Recipe[] = [];
  showDetail: boolean = false;
  recipeSelected !: Recipe;

  constructor(private router:Router, private activatedroute: ActivatedRoute, private recipeBooksService:RecipeBooksService) {
    this.idRecipeBook = Number(this.activatedroute.snapshot.paramMap.get('id'));
  }

  ngOnInit(): void {
    this.idRecipeBook = Number(this.activatedroute.snapshot.paramMap.get('id'));
    if(Number.isNaN(this.idRecipeBook)) this.router.navigate(['/error']);

    this.recipeBooksService.getRecipeBook(this.idRecipeBook);

    this.recipes = this.recipeBooksService.getRecipesFromBook(this.idRecipeBook);
    
  }

  public showRecipeDetail(id:number){
    this.showDetail=true;
    let recipeAux = this.recipes.find(recipe => recipe.id == id);
    if(recipeAux != undefined)
      this.recipeSelected = recipeAux;
  }

  public closeRecipeDetail(){
    this.showDetail = false;
  }
}
