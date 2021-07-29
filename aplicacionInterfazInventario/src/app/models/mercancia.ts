import { Usuario } from "./Usuario";

export class Mercancia {
    id: number;
    nombre: string;
    cantidad: number;
    fechaIngreso : string;
    fechaModificacion : string;
    usuarioRegistro: Usuario[] = [];
    usuarioModificacion: Usuario[] = [];

    constructor( id: number, nombre: string, cantidad: number, fechaIngreso : string, fechaModificacion : string,  usuarioRegistro: Usuario[], usuarioModificacion: Usuario[]){
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaIngreso = fechaIngreso;
        this.fechaModificacion = fechaModificacion;
        this.usuarioRegistro = usuarioRegistro;
        this.usuarioModificacion = usuarioModificacion;
    }

}
