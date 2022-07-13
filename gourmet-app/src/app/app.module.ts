import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecipebooksComponent } from './components/recipebooks/recipebooks.component';
import { PresentationComponent } from './components/presentation/presentation.component';
import { SearchComponent } from './components/search/search.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { PageNotFoundComponent } from './components/pageNotFound/pagenotfound.component';
import { RecipeDetailComponent } from './components/recipe-detail/recipe-detail.component';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RecipebookformComponent } from './components/recipebookform/recipebookform.component';
import { RecipeformComponent } from './components/recipeform/recipeform.component';

@NgModule({
  declarations: [
    AppComponent,
    RecipebooksComponent,
    PresentationComponent,
    SearchComponent,
    RecipesComponent,
    PageNotFoundComponent,
    RecipeDetailComponent,
    RecipebookformComponent,
    RecipeformComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
