import { Usuario } from "./Usuario";

export class Mercancia {
    id: number;
    nombre: string;
    cantidad: number;
    fechaIngreso : string;
    fechaModificacion : string;
    usuarioRegistro: Usuario[] = [];
    usuarioModificacion: Usuario[] = [];


}
