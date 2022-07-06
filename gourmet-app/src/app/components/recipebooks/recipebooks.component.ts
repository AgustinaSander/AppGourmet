import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RecipeBook } from 'src/app/model/Recipebook';

@Component({
  selector: 'app-recipebooks',
  templateUrl: './recipebooks.component.html',
  styleUrls: ['./recipebooks.component.scss']
})
export class RecipebooksComponent implements OnInit {

  recipebooks: RecipeBook[] = [];

  constructor(private router:Router) {
    for(let i = 1; i<9 ; i++)
      this.recipebooks.push(new RecipeBook(i, "RecipeBook "+i, []));
    
  }

  ngOnInit(): void {
  }

  showRecipeBook(id:number){
    this.router.navigate(['/recipebooks', id]);
  }

}
