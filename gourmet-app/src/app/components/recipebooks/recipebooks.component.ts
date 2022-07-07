import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RecipeBook } from 'src/app/model/Recipebook';
import { RecipeBooksService } from 'src/app/services/recipe-books.service';

@Component({
  selector: 'app-recipebooks',
  templateUrl: './recipebooks.component.html',
  styleUrls: ['./recipebooks.component.scss']
})
export class RecipebooksComponent implements OnInit {

  recipebooks: RecipeBook[] = [];

  constructor(private router:Router, private recipeBooksService:RecipeBooksService) {
  }

  ngOnInit() { 
    this.recipebooks = this.recipeBooksService.getRecipeBooks();
  }

  showRecipeBook(id:number){
    this.router.navigate(['/recipebooks', id]);
  }

}
