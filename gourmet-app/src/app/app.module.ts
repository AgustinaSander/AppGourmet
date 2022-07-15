import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecipebooksComponent } from './components/recipebooks/recipebooks.component';
import { PresentationComponent } from './components/presentation/presentation.component';
import { SearchComponent } from './components/search/search.component';
import { RecipesFromBookComponent } from './components/recipes-from-book/recipes-from-book.component';
import { PageNotFoundComponent } from './components/pageNotFound/pagenotfound.component';
import { RecipeDetailComponent } from './components/recipe-detail/recipe-detail.component';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RecipebookformComponent } from './components/recipebookform/recipebookform.component';
import { RecipeformComponent } from './components/recipeform/recipeform.component';
import * as bootstrap from "bootstrap";
import { AllRecipesComponent } from './components/all-recipes/all-recipes.component';
import { AddRecipeToBookFormComponent } from './components/add-recipe-to-book-form/add-recipe-to-book-form.component';

@NgModule({
  declarations: [
    AppComponent,
    RecipebooksComponent,
    PresentationComponent,
    SearchComponent,
    RecipesFromBookComponent,
    AllRecipesComponent,
    PageNotFoundComponent,
    RecipeDetailComponent,
    RecipebookformComponent,
    RecipeformComponent,
    AddRecipeToBookFormComponent
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
