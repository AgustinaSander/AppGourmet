import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Food } from 'src/app/model/Food';
import { FoodQuantity } from 'src/app/model/FoodQuantity';
import { Recipe } from 'src/app/model/Recipe';
import { RecipeBook } from 'src/app/model/Recipebook';
import { RecipeBooksService } from 'src/app/services/recipe-books.service';

@Component({
  selector: 'app-recipes-from-book',
  templateUrl: './recipes-from-book.component.html',
  styleUrls: ['./recipes-from-book.component.scss']
})
export class RecipesFromBookComponent implements OnInit {
  recipeBook: RecipeBook = new RecipeBook();
  showDetail: boolean = false;
  recipeSelected !: Recipe;

  constructor(private router:Router, private activatedroute: ActivatedRoute, private recipeBooksService:RecipeBooksService) {
  }

  ngOnInit(): void {
    let id = Number(this.activatedroute.snapshot.paramMap.get('id'));
    if(Number.isNaN(id)) this.router.navigate(['/error']);
    this.recipeBook.setId(id);
    this.updateView();
  }
 
  public showRecipeDetail(id:number){
    this.showDetail=true;
    let recipeAux = this.recipeBook.getListRecipes().find(recipe => recipe.id == id);
    if(recipeAux != undefined)
      this.recipeSelected = recipeAux;
  }

  public closeRecipeDetail(){
    this.showDetail = false;
  }

  updateView(){
    this.recipeBook.setRecipes([]);
    this.recipeBook.setTitle("");

    this.recipeBooksService.getRecipeBook(this.recipeBook.getId()).subscribe({
      next: response => {
        if(response.listRecipes != undefined){
          let recipes: Recipe[] = [];
          for(let recipeItem of response.listRecipes){
            let recipe = new Recipe(recipeItem.id, recipeItem.title);
            let foodquantityList = [];
            for(let foodquantityItem of recipeItem.foodQuantity){
              let food = new Food(foodquantityItem.food.id, foodquantityItem.food.name, foodquantityItem.food.calories, foodquantityItem.food.foodGroup, foodquantityItem.food.unit.toLowerCase());
              let foodquantity = new FoodQuantity(foodquantityItem.quantity, food);
              foodquantityList.push(foodquantity);
            }
            recipe.setFoodQuantity(foodquantityList);
            recipes.push(recipe);
          }
          this.recipeBook.setRecipes(recipes);
          this.recipeBook.setTitle(response.title);
        };
    }, error:error => {
      this.router.navigate(['/error']);
    }});
  }
}
