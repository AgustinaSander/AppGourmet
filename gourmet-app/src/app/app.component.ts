import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NavigateRoutes } from './navigateRoutes';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent{
  title = 'gourmet-app';

  constructor(private router:Router){}
  
  showRecipeBooks(){
    this.router.navigate([NavigateRoutes.urlRecipeBooks]);
  }

  showRecipes(){
    this.router.navigate([NavigateRoutes.urlRecipes]);
  }
}


