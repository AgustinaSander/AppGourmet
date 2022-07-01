import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { first } from 'rxjs';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  @Output() showRecipeBooksEmitter = new EventEmitter<boolean>();;
  private showRecipeBooks = false;

  constructor() { }

  ngOnInit(): void {
  }

  public clicked(){
    this.showRecipeBooks = !this.showRecipeBooks;
    this.showRecipeBooksEmitter.emit(this.showRecipeBooks);
    this.showRecipeBooks ? $(".search-container a").html("Ocultar recetarios") : $(".search-container a").html("Mostrar recetarios");
  }
}
