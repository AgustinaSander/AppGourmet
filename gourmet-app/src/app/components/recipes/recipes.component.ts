import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Food } from 'src/app/model/Food';
import { FoodQuantity } from 'src/app/model/FoodQuantity';
import { Recipe } from 'src/app/model/Recipe';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.scss']
})
export class RecipesComponent implements OnInit {
  idRecipeBook:number = 0;
  recipes: Recipe[] = [];
  showRecipeDetail: boolean = true;

  constructor(private router:Router, private activatedroute: ActivatedRoute) {
    this.idRecipeBook = Number(this.activatedroute.snapshot.paramMap.get('id'));

    let foodQuantities:FoodQuantity[] = [];
    for(let i=1; i<5; i++){
      let food: Food = new Food(i, "Tomate", 123, "Vegetales", "UNIT");
      foodQuantities.push(new FoodQuantity(i+2, food));
    }

    for(let i = 1; i<9 ; i++)
      this.recipes.push(new Recipe(i, "Recipe "+i+" of recipebook "+this.idRecipeBook, foodQuantities));
  }

  ngOnInit(): void {
    this.idRecipeBook = Number(this.activatedroute.snapshot.paramMap.get('id'));
    if(Number.isNaN(this.idRecipeBook)) this.router.navigate(['/error']);
  }

}
