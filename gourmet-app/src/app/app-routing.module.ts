import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './components/pageNotFound/pagenotfound.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { SearchComponent } from './components/search/search.component';

const routes: Routes = [
  {path: '', component: SearchComponent, data:{show:false}},
  {path: 'recipebooks', component: SearchComponent, data:{show:true}},
  {path: 'recipebooks/:id', component: RecipesComponent},
  {path: 'error', component:PageNotFoundComponent},
  {path: '**', redirectTo:'/error'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
