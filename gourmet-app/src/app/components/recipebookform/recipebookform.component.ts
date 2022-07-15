import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RecipeBook } from 'src/app/model/Recipebook';
import { RecipeBooksService } from 'src/app/services/recipe-books.service';

@Component({
  selector: 'app-recipebookform',
  templateUrl: './recipebookform.component.html',
  styleUrls: ['./recipebookform.component.scss']
})
export class RecipebookformComponent implements OnInit {
  @ViewChild('recipeBookForm') form!: NgForm;
  @Output() updateView = new EventEmitter();

  addedSuccessfully:boolean = false;

  constructor(private recipeBooksService:RecipeBooksService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    let recipeBook = new RecipeBook();
    recipeBook.setTitle(this.form.controls['title'].value);
    this.recipeBooksService.addRecipeBook(recipeBook).subscribe({
        next: () => {
          console.log("Saving recipe book..");
          this.form.reset();
          this.addedSuccessfully = true;
          this.updateView.emit();
        },
        error: error => {
            console.error('There was an error!', error);
        }
      });
  }

  closeForm(){
    this.addedSuccessfully=false;
    if(this.form!=undefined) this.form.reset();
  }
}
