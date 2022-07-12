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

    this.recipeBooksService.getRecipeBook(this.idRecipeBook).subscribe({
      next: () => {
        this.recipeBooksService.getRecipesFromBook(this.idRecipeBook).subscribe((response) => {
          if(response._embedded != undefined){
            for(let recipeItem of response._embedded.recipeDTOes){
              let recipe = new Recipe(recipeItem.id, recipeItem.title);
                
                let foodquantityList = [];
                for(let foodquantityItem of recipeItem.foodQuantity){
                  
                  let food = new Food(foodquantityItem.food.id, foodquantityItem.food.name, foodquantityItem.food.calories, foodquantityItem.food.foodGroup, foodquantityItem.food.unit.toLowerCase());
                  let foodquantity = new FoodQuantity(foodquantityItem.quantity, food);
                  foodquantityList.push(foodquantity);
                }
                recipe.setFoodQuantities(foodquantityList);
                this.recipes.push(recipe);
              }
          }
        });
    }, error:error => {
      this.router.navigate(['/error']);
    }});

    
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
