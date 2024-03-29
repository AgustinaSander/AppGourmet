import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Food } from 'src/app/model/Food';
import { FoodQuantity } from 'src/app/model/FoodQuantity';
import { Recipe } from 'src/app/model/Recipe';
import { RecipeBook } from 'src/app/model/Recipebook';
import { NavigateRoutes } from 'src/app/navigateRoutes';
import { RecipeBooksService } from 'src/app/services/recipe-books.service';
import { RecipebookformComponent } from '../recipebookform/recipebookform.component';

@Component({
  selector: 'app-recipebooks',
  templateUrl: './recipebooks.component.html',
  styleUrls: ['./recipebooks.component.scss']
})
export class RecipebooksComponent implements OnInit {
  @ViewChild(RecipebookformComponent)
  private recipebookForm!: RecipebookformComponent;

  recipebooks: RecipeBook[] = [];

  constructor(private router:Router, private recipeBooksService:RecipeBooksService) {
  }

  ngOnInit() { 
    this.updateView();
  }

  showRecipeBook(id:number){
    this.router.navigate([`${NavigateRoutes.urlRecipeBooks}/${id}`]);
  }

  updateView(){
    this.recipebooks = [];
    this.recipeBooksService.getRecipeBooks().subscribe(response => {
      for(let item of response._embedded.recipeBookDTOes){
        let recipebook = new RecipeBook(item.id, item.title);
       
        let recipesList = [];
        for(let recipeItem of item.listRecipes){
          let recipe = new Recipe(recipeItem.id, recipeItem.title);
          
          let foodquantityList = [];
          for(let foodquantityItem of recipeItem.foodQuantity){
            
            let food = new Food(foodquantityItem.food.id, foodquantityItem.food.name, foodquantityItem.food.calories, foodquantityItem.food.foodGroup, foodquantityItem.food.unit);
            let foodquantity = new FoodQuantity(foodquantityItem.quantity, food);
            foodquantityList.push(foodquantity);
          }
          recipe.setFoodQuantity(foodquantityList);
          recipesList.push(recipe);
        }
        recipebook.setRecipes(recipesList);
        this.recipebooks.push(recipebook);
      }
    })

    setTimeout(()=>{
      $('#recipeBookForm').modal('hide');
      this.recipebookForm.closeForm();
    },500);
  }
}
