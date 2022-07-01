import { Component, OnInit } from '@angular/core';
import { RecipeBook } from 'src/app/model/Recipebook';
/* import {RecipeBook} from '../../model/recipebook'; */

@Component({
  selector: 'app-recipebooks',
  templateUrl: './recipebooks.component.html',
  styleUrls: ['./recipebooks.component.scss']
})
export class RecipebooksComponent implements OnInit {

  recipebooks: RecipeBook[] = [];

  constructor() {
    for(let i = 1; i<9 ; i++)
      this.recipebooks.push(new RecipeBook(i, "RecipeBook "+i, []));
    
  }

  ngOnInit(): void {
  }

}
