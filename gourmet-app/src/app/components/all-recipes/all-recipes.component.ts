import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Food } from 'src/app/model/Food';
import { FoodQuantity } from 'src/app/model/FoodQuantity';
import { Recipe } from 'src/app/model/Recipe';
import { RecipesService } from 'src/app/services/recipes.service';
import { RecipeformComponent } from '../recipeform/recipeform.component';


@Component({
  selector: 'app-all-recipes',
  templateUrl: './all-recipes.component.html',
  styleUrls: ['./all-recipes.component.scss']
})
export class AllRecipesComponent implements OnInit {
  @ViewChild(RecipeformComponent)
  private addRecipeForm!: RecipeformComponent;

  showDetail: boolean = false;
  recipes : Recipe[] = [];
  recipeSelected !: Recipe;

  constructor(private router:Router, private activatedroute: ActivatedRoute, private recipesService:RecipesService) {
  }

  ngOnInit(): void {
    this.updateView();
  }
 
  public showRecipeDetail(id:number){
    this.router.navigateByUrl('/recipes/'+ id);
  }

  updateView(){
    this.recipes = [];
    this.recipesService.getAllRecipes().subscribe(response => {
      for(let recipeItem of response._embedded.recipeDTOes){
        let recipe = new Recipe(recipeItem.id, recipeItem.title);
          
        let foodquantityList = [];
        for(let foodquantityItem of recipeItem.foodQuantity){
          
          let food = new Food(foodquantityItem.food.id, foodquantityItem.food.name, foodquantityItem.food.calories, foodquantityItem.food.foodGroup, foodquantityItem.food.unit);
          let foodquantity = new FoodQuantity(foodquantityItem.quantity, food);
          foodquantityList.push(foodquantity);
        }
        recipe.setFoodQuantity(foodquantityList);
        this.recipes.push(recipe);
      }
    })

    setTimeout(()=>{
      $('#addRecipeForm').modal('hide');
      this.addRecipeForm.closeForm();
    },1000);
  }
}
