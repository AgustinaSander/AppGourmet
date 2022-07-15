import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Food } from 'src/app/model/Food';
import { FoodQuantity } from 'src/app/model/FoodQuantity';
import { Recipe } from 'src/app/model/Recipe';
import { RecipeBooksService } from 'src/app/services/recipe-books.service';
import { RecipesService } from 'src/app/services/recipes.service';

@Component({
  selector: 'app-add-recipe-to-book-form',
  templateUrl: './add-recipe-to-book-form.component.html',
  styleUrls: ['./add-recipe-to-book-form.component.scss']
})
export class AddRecipeToBookFormComponent implements OnInit {
  @ViewChild('addRecipeToBookForm') addRecipeToBookForm!: NgForm;
  @Output() updateView = new EventEmitter();
  addedSuccessfully:boolean = false;
  recipesSelected:Recipe[] = [];
  recipes:Recipe[] = [];

  constructor(private recipebooksService:RecipeBooksService, private recipesService:RecipesService) { }

  ngOnInit(): void {
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
  }

  onSubmit() {
    /* let recipe = new Recipe();
    recipe.setTitle(this.recipeForm.controls['title'].value);
    recipe.setFoodQuantity(this.listFoodQuantities);
    this.recipesService.addRecipe(recipe).subscribe({
        next: () => {
          console.log("Adding recipe..");
          this.recipeForm.reset();
          this.addedSuccessfully=true;
          this.updateView.emit();
        },
        error: error => {
            console.error('There was an error!', error);
        }
      }); */
  }

  closeForm(){
    this.addedSuccessfully=false;
    if(this.addRecipeToBookForm!=undefined) this.addRecipeToBookForm.reset();
    this.recipesSelected = [];
    this.recipes = [];
  }

}
