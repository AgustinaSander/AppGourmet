import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecipebooksComponent } from './components/recipebooks/recipebooks.component';

const routes: Routes = [
  {path: 'recipebooks', component: RecipebooksComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
