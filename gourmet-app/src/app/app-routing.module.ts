import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllRecipesComponent } from './components/all-recipes/all-recipes.component';
import { PageNotFoundComponent } from './components/pageNotFound/pagenotfound.component';
import { RecipesFromBookComponent } from './components/recipes-from-book/recipes-from-book.component';
import { SearchComponent } from './components/search/search.component';

const routes: Routes = [
  {path: '', component: SearchComponent, data:{show:false}},
  {path: 'recipebooks', component: SearchComponent, data:{show:true}},
  {path: 'recipebooks/:id', component: RecipesFromBookComponent},
  {path: 'recipebooks/:id/recipes', redirectTo:'recipebooks/:id'},
  {path: 'recipes', component: AllRecipesComponent},
  {path: 'error', component:PageNotFoundComponent},
  {path: '**', redirectTo:'/error'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
