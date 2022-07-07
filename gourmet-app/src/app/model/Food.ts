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

    toString():string{
        return "Food [id=" + this.id + ", name=" + this.name + ", calories=" + this.calories + ", foodGroup=" + this.foodgroup + ", unit="
				+ this.unit + "]";
    }
}