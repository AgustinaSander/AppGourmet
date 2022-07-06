export class Food{
    id!:number;
    name!:string;
    calories!:number;
    foodgroup!:string;
    unit!:string;

    constructor(id:number, name:string, calories:number, foodgroup:string, unit:string){
        this.id=id;
        this.name=name;
        this.calories=calories;
        this.foodgroup=foodgroup;
        this.unit=unit;
    }
}