import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Recipe } from 'src/app/model/Recipe';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.scss']
})
export class RecipeDetailComponent implements OnInit {

  @Input() recipeItem!: Recipe;
  @Output() closeRecipeDetail = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  closeDetail(){
    this.closeRecipeDetail.emit();
  }
}
