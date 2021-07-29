export class Usuario{
    id: number;
    nombre:string; 
    edad:number;
    fechaIngreso:string; 

    constructor(id: number, nombre: string, edad: number, fechaIngreso:string){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.fechaIngreso = fechaIngreso;

    }
 
}
