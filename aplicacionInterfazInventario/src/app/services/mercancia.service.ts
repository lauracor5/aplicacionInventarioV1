import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Mercancia } from '../models/mercancia';
import { Usuario } from '../models/Usuario';

@Injectable({
  providedIn: 'root'
})
export class MercanciaService {

  private baseEndPoint = 'http://localhost:8080/api/inventario/mercancia';
  private baseEndPointUsuario = 'http://localhost:8080/api/inventario/usuario';
  private cabeceras: HttpHeaders = new HttpHeaders({ 'Content.Type': 'application/json' })
  constructor(private http: HttpClient) { }

  public listar(): Observable<Mercancia[]> {
    return this.http.get<Mercancia[]>(this.baseEndPoint);
  }

  public listarPaginas(page: string, size: string): Observable<any> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size);
    return this.http.get<any>(`${this.baseEndPoint}/pagina`, { params: params });
  }

  public ver(id: number): Observable<Mercancia> {
    return this.http.get<Mercancia>(this.baseEndPoint + '/' + id);
  }


  public crear(mercancia: Mercancia): Observable<Mercancia> {
    return this.http.post<Mercancia>(this.baseEndPoint, mercancia, { headers: this.cabeceras });
  }

  public editar(mercancia: Mercancia): Observable<Mercancia> {
    return this.http.put<Mercancia>(`${this.baseEndPoint}/${mercancia.id}`, mercancia, { headers: this.cabeceras });
  }

  public eliminar(id: number, idUsuario: number): Observable<void> {
    return this.http.delete<void>(`${this.baseEndPoint}/${id}/${idUsuario}`);
  }

  public findAllUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.baseEndPointUsuario)
  }

  public finByIdUsuario(id:number): Observable<Usuario>{
    return this.http.get<Usuario>(this.baseEndPointUsuario + '/' + id);
  }


}
