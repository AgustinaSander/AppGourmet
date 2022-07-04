import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router, RoutesRecognized } from '@angular/router';
import { data } from 'jquery';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  public showRecipeBooks:boolean = false;

  changeRecipeBooks(){
    this.showRecipeBooks ? this.hide() : this.show();
  }

  show(){
    this.showRecipeBooks = true;
    $(".search-container a").html("Ocultar recetarios");
    this.router.navigate(['/recipebooks']);
  }

  hide(){
    this.showRecipeBooks = false;
    $(".search-container a").html("Mostrar recetarios");
    this.router.navigate(['/']);
  }

  constructor(private router: Router, private activatedroute:ActivatedRoute) {

  }

  ngOnInit(): void {
    this.activatedroute.data.subscribe(data => {
      data['show'] ? this.show() : this.hide();
    })
  }
}

