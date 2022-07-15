import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Food } from 'src/app/model/Food';
import { FoodQuantity } from 'src/app/model/FoodQuantity';
import { Recipe } from 'src/app/model/Recipe';
import { RecipeBook } from 'src/app/model/Recipebook';
import { NavigateRoutes } from 'src/app/navigateRoutes';
import { RecipeBooksService } from 'src/app/services/recipe-books.service';
import { AddRecipeToBookFormComponent } from '../add-recipe-to-book-form/add-recipe-to-book-form.component';

@Component({
  selector: 'app-recipes-from-book',
  templateUrl: './recipes-from-book.component.html',
  styleUrls: ['./recipes-from-book.component.scss']
})
export class RecipesFromBookComponent implements OnInit {
  @ViewChild(AddRecipeToBookFormComponent)
  private addRecipeToBookForm!: AddRecipeToBookFormComponent;
  
  recipeBookSelected: RecipeBook = new RecipeBook();
  showDetail: boolean = false;
  recipeSelected !: Recipe;

  constructor(private router:Router, private activatedroute: ActivatedRoute, private recipeBooksService:RecipeBooksService) {
  }

  ngOnInit(): void {
    let id = Number(this.activatedroute.snapshot.paramMap.get('id'));
    if(Number.isNaN(id)) this.router.navigate([NavigateRoutes.urlError]);
    this.recipeBookSelected.setId(id);
    this.updateView();
  }
 
  public showRecipeDetail(id:number){
    this.showDetail=true;
    let recipeAux = this.recipeBookSelected.getListRecipes().find(recipe => recipe.id == id);
    if(recipeAux != undefined)
      this.recipeSelected = recipeAux;
  }

  public closeRecipeDetail(){
    this.showDetail = false;
  }

  updateView(){
    this.recipeBookSelected.setRecipes([]);
    this.recipeBookSelected.setTitle("");

    this.recipeBooksService.getRecipeBook(this.recipeBookSelected.getId()).subscribe({
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
          this.recipeBookSelected.setRecipes(recipes);
          this.recipeBookSelected.setTitle(response.title);
        };
    }, error:error => {
      this.router.navigate([NavigateRoutes.urlError]);
    }});

    setTimeout(()=>{
      $('#addRecipeToBookForm').modal('hide');
      this.addRecipeToBookForm.closeForm();
    },500);
  }
}
