import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Food } from 'src/app/model/Food';
import { FoodQuantity } from 'src/app/model/FoodQuantity';
import { Recipe } from 'src/app/model/Recipe';
import { RecipeBook } from 'src/app/model/Recipebook';
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
  @Input() recipeBookSelected!:RecipeBook;

  addedSuccessfully:boolean = false;
  recipesSelected:Recipe[] = [];
  recipes:Recipe[] = [];

  constructor(private recipebooksService:RecipeBooksService, private recipesService:RecipesService) { }

  ngOnInit(): void {
    this.getAllRecipes();
  }

  getAllRecipes(){
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
  }

  onChangeCheckbox(target:any){
    if(target.checked){
      this.addRecipeWithId(target.value);
    }
    else{
      this.removeRecipeWithId(target.value);
    }
  }

  addRecipeWithId(id:number){
    let recipeToAdd = this.recipes.find(recipe => recipe.id == id);
    if(recipeToAdd != undefined)
      this.recipesSelected.push(recipeToAdd);
  }

  removeRecipeWithId(id:number){
    let recipeToRemove = this.recipes.find(recipe => recipe.id == id);
    if(recipeToRemove != undefined){
      let indexToRemove = this.recipesSelected.indexOf(recipeToRemove);
      this.recipesSelected.splice(indexToRemove, 1);
    }
  }

  onSubmit() {
    this.recipebooksService.addRecipeInRecipeBook(this.recipesSelected,this.recipeBookSelected.getId()).subscribe({
      next: () => {
        console.log("Adding recipes..");
        this.addedSuccessfully=true;
        this.updateView.emit();
      },
      error: error => {
          console.error('There was an error!', error);
      }
    });
  }

  closeForm(){
    this.addedSuccessfully=false;
    this.recipesSelected = [];
    this.getAllRecipes();
  }

}
