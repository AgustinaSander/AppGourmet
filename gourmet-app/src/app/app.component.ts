import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RecipeBooksService } from './services/recipe-books.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent{
  title = 'gourmet-app';

  constructor(private router:Router){}
  
  showRecipeBooks(){
    this.router.navigate(['/recipebooks']);
  }

  showRecipes(){
    this.router.navigate(['/recipes']);
  }
}


