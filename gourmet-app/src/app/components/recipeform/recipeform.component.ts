import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Food } from 'src/app/model/Food';
import { FoodGroup } from 'src/app/model/FoodGroup';
import { FoodQuantity } from 'src/app/model/FoodQuantity';
import { Recipe } from 'src/app/model/Recipe';
import { Unit } from 'src/app/model/Unit';
import { RecipesService } from 'src/app/services/recipes.service';

@Component({
  selector: 'app-recipeform',
  templateUrl: './recipeform.component.html',
  styleUrls: ['./recipeform.component.scss']
})
export class RecipeformComponent implements OnInit {
  @ViewChild('recipeForm') recipeForm!: NgForm;
  @ViewChild('ingredientForm') ingredientForm!: NgForm;
  @Output() updateView = new EventEmitter();
  addedSuccessfully:boolean = false;

  unitOptions = Unit;
  foodGroupOptions = FoodGroup;
  listFoodQuantities:FoodQuantity[] = new Array();

  constructor(private recipesService:RecipesService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    let recipe = new Recipe();
    recipe.setTitle(this.recipeForm.controls['title'].value);
    recipe.setFoodQuantity(this.listFoodQuantities);
    this.recipesService.addRecipe(recipe).subscribe({
        next: () => {
          this.recipeForm.reset();
          this.addedSuccessfully=true;
          this.updateView.emit();
        },
        error: error => {
            console.error('There was an error!', error);
        }
      });
  }

  addIngredient(){
    let data = this.ingredientForm.controls;
    let foodData = new Food(0, data['food'].value, data['calories'].value, data['foodGroupSelect'].value, data['unitSelect'].value);
    let foodQuantityData = new FoodQuantity(data['quantity'].value, foodData);
    this.listFoodQuantities.push(foodQuantityData);
    this.ingredientForm.reset();
  }

  deleteIngredient(i:number){
    this.listFoodQuantities.splice(i,1);
  }

  closeForm(){
    this.addedSuccessfully=false;
    if(this.recipeForm!=undefined) this.recipeForm.reset();
    this.listFoodQuantities = [];
  }

}
