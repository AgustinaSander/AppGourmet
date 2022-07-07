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

@NgModule({
  declarations: [
    AppComponent,
    RecipebooksComponent,
    PresentationComponent,
    SearchComponent,
    RecipesComponent,
    PageNotFoundComponent,
    RecipeDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
