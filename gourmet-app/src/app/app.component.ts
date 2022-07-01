import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent{
  title = 'gourmet-app';
  showSearch:boolean = true;
  showRecipeBooks:boolean = false;

  changeRecipeBooks(show: boolean){
    this.showRecipeBooks = show;
  }
}

