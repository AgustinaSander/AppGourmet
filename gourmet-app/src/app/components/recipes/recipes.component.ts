import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.scss']
})
export class RecipesComponent implements OnInit {
  id:number = 0;
  constructor(private router:Router, private activatedroute: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = Number(this.activatedroute.snapshot.paramMap.get('id'));
    if(Number.isNaN(this.id)) this.router.navigate(['/recipebooks']);
  }

}
