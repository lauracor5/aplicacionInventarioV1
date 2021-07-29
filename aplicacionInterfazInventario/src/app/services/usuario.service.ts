import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../models/Usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private baseEndPoint = 'http://localhost:8080/api/inventario/usuario';
  private cabeceras: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'})
  constructor(private http: HttpClient) { }

  public listar(): Observable<Usuario[]>{
    return this.http.get<Usuario[]>(this.baseEndPoint);
  }

  public listarPaginas(page: string, size: string):Observable<any>{
    const params = new HttpParams()
    .set('page', page)
    .set('size', size);
    return this.http.get<any>(`${this.baseEndPoint}/pagina`, {params: params})
  }

  public ver(id: number): Observable<Usuario>{
    return this.http.get<Usuario>(this.baseEndPoint + '/' + id);
  }

  public crear(usuario:Usuario): Observable<Usuario>{
    return this.http.post<Usuario>(this.baseEndPoint, usuario, { headers: this.cabeceras });
  }
  
  public editar(usuario:Usuario): Observable<Usuario>{
    return this.http.put<Usuario>(`${this.baseEndPoint}/${usuario.id}`, usuario, { headers: this.cabeceras });
  }

  public eliminar(id:number):Observable<void>{
    return this.http.delete<void>(`${this.baseEndPoint}/${id}`);
  }
}
