import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router, RoutesRecognized } from '@angular/router';
import { data } from 'jquery';
import { NavigateRoutes } from 'src/app/navigateRoutes';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  showRecipeBooks:boolean = false;
  buttonText = "Mostrar recetarios";

  changeRecipeBooks(){
    this.showRecipeBooks ? this.hide() : this.show();
  }

  show(){
    this.showRecipeBooks = true;
    this.buttonText = "Ocultar recetarios";
    this.router.navigate([NavigateRoutes.urlRecipeBooks]);
  }

  hide(){
    this.showRecipeBooks = false;
    this.buttonText = "Mostrar recetarios";
    this.router.navigate(['/']);
  }

  constructor(private router: Router, private activatedroute:ActivatedRoute) {  }

  ngOnInit(): void {
    this.activatedroute.data.subscribe(data => {
      data['show'] ? this.show() : this.hide();
    })
  }
}

