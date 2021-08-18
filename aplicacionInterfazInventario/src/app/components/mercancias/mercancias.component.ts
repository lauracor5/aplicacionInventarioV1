import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Mercancia } from 'src/app/models/mercancia';
import { Usuario } from 'src/app/models/Usuario';
import { MercanciaService } from 'src/app/services/mercancia.service';

@Component({
  selector: 'app-mercancias',
  templateUrl: './mercancias.component.html',
  styleUrls: ['./mercancias.component.css']
})
export class MercanciasComponent implements OnInit {
 mercancias: Mercancia[] =[];

 totalRegistros = 0;
 paginaActual = 0;
 totalPorPagina = 4;
 pageSizeOptions: number[] = [3, 5, 10, 25, 100];
 nombreUsuarioRegistro = '';
 nombreUsuarioModificacion = '';


 @ViewChild(MatPaginator)paginator: MatPaginator;

  constructor(private service:MercanciaService) { }

  ngOnInit(): void {
   this.calcularRango();

   
  }

  paginar(event: PageEvent):void{
    this.paginaActual = event.pageIndex;
    console.log(event.pageIndex);
    this.totalPorPagina = event.pageSize;
    this.calcularRango();

  }

  private calcularRango =() =>{
    this.service.listarPaginas(this.paginaActual.toString(),
    this.totalPorPagina.toString())
    .subscribe(p => {
      this.mercancias = p.content as Mercancia[];
      console.log(this.mercancias);
      this.validaUsuarioModificacionNull();
      console.log('valor de p', p);
      this.totalRegistros = p.totalElements as number;
      this.paginator._intl.itemsPerPageLabel = 'registros por pagina'
    });
  }


  private validaUsuarioModificacionNull = () => {
    for(let i = 0; i < this.mercancias.length; i++) {
        if(this.mercancias[i].usuarioModificacion?.nombre === null){
          this.nombreUsuarioModificacion = '';
        }else{
          this.nombreUsuarioModificacion = this.mercancias[i].usuarioModificacion?.nombre;
        }
   }

   console.log('usuario mod', this.nombreUsuarioModificacion)

   return this.nombreUsuarioModificacion;
}


}
